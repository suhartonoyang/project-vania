package com.project.bayes.vania.service;

import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import com.project.bayes.vania.bean.Attribute;
import com.project.bayes.vania.bean.DataSet;
import com.project.bayes.vania.bean.MapAttributResult;
import com.project.bayes.vania.bean.Request;
import com.project.bayes.vania.bean.Result;

@Service
public class BayesService {
	/**
	 * @param args the command line arguments
	 */
	static double PGo_out = 0.5;
	static double PStay_home = 0.5;

	static double PSunnyGo_out;
	static double PRainyGo_out;
	static double PSunnyStay_home;
	static double PRainyStay_home;

	static double PWorkingGo_out;
	static double PBrokenGo_out;
	static double PWorkingStay_home;
	static double PBrokenStay_home;

	static String[] Wheater = new String[10];
	static String[] Car = new String[10];
	static String[] Class = new String[10];

	static Map Wheater_Temp = new HashMap<>();
	static Map Car_Temp = new HashMap<>();
	static Map Class_Temp = new HashMap<>();

	public static void main(String[] args) throws IOException {
		// TODO code application logic here

		String[] Wheater = { "sunny", "rainy", "sunny", "sunny", "sunny", "rainy", "rainy", "sunny", "sunny", "rainy" };
		String[] Car = { "working", "broken", "working", "working", "working", "broken", "broken", "working", "broken",
				"broken" };
		String[] Class = { "go-out", "go-out", "go-out", "go-out", "go-out", "stay-home", "stay-home", "stay-home",
				"stay-home", "stay-home" };
		
		//generate data from database
		List<DataSet> dataSetList = generateData(Wheater, Car, Class);

		// process data to count total value attribute and prob
		List<Attribute> attrs = getValueAttrs(dataSetList);
		Map<String, Integer> mapCountResult = countResult(dataSetList);
		Map<String, Double> mapProbResult = probResult(mapCountResult, attrs);
		List<MapAttributResult> mapAttributeResults = countAttrs(dataSetList, attrs, mapCountResult);

		// request to check prediction
		Request request = new Request("weather", "sunny");
		List<Request> requests = new ArrayList();
		requests.add(request);
		Request request1 = new Request("car", "working");
		requests.add(request1);
		
		Result resultPrediction = prediction(requests, attrs, mapAttributeResults, mapProbResult);
		
		System.out.println("kesimpulan: " + resultPrediction.getName());
	}

	private static List<DataSet> generateData(String[] wheater, String[] car, String[] result) {
		List<DataSet> dataSetList = new ArrayList<>();
		int id = 0;
		for (int i = 0; i < wheater.length; i++) {
			for (int j = 0; j < car.length; j++) {
				if (i == j) {
					for (int k = 0; k < result.length; k++) {
						if (i == k) {
							++id;
							DataSet dataSet = new DataSet(id, wheater[i], car[j], result[k]);
							dataSetList.add(dataSet);
						}
					}
				}
			}
		}

//		dataSetList.stream().forEach(p -> {
//			System.out.println(p.getId() + " - " + p.getWeather() + " - " + p.getCar() + " - " + p.getResult());
//		});

		return dataSetList;

	}

	private static List<Attribute> getValueAttrs(List<DataSet> dataSets) {
		List<Attribute> attrs = new ArrayList<Attribute>();
		Field[] fields = DataSet.class.getDeclaredFields();
		for (Field field : fields) {
			List<String> valueAttrs = new ArrayList<>();
			if (!field.getName().equalsIgnoreCase("id")) {
				dataSets.stream().map(p -> {
					switch (field.getName()) {
					case "weather":
						return p.getWeather();
					case "car":
						return p.getCar();
					case "result":
						return p.getResult();
					default:
						return null;
					}
				}).distinct().forEach(q -> {
					valueAttrs.add(q);
				});
				Attribute attr = new Attribute(field.getName(), valueAttrs);
				attrs.add(attr);
			}
		}

//		attrs.forEach(p -> {
//			System.out.println("attribute: " + p.getName());
//			p.getValues().forEach(q -> {
//				System.out.println("values: " + q);
//			});
//		});
		return attrs;
	}

	public static Map<String, Integer> countResult(List<DataSet> dataSet) {
		List<DataSet> dataSetTemp = dataSet;
		Map<String, Integer> map = new HashMap<String, Integer>();
		dataSetTemp.stream().map(p -> p.getResult()).distinct().forEach(e -> {
			int hitung = dataSet.stream().filter(p -> p.getResult().equals(e.toString())).collect(Collectors.toList())
					.size();
			map.put(e, hitung);
		});
//		map.entrySet().forEach(p -> {
//			System.out.println("Jumlah Kelas " + p.getKey() + ": " + p.getValue());
//		});
		return map;
	}

	public static Map<String, Double> probResult(Map<String, Integer> countResult, List<Attribute> attrs) {
		Map<String, Double> probResult = new HashMap<String, Double>();
		List<String> results = new ArrayList<String>();
		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("result")).map(q -> q.getValues()).forEach(r -> {
			results.addAll(r);
		});

		for (String result : results) {
			int sumAll = countResult.values().stream().mapToInt(Integer::intValue).sum();
			int value = countResult.get(result);
			double prob = (double) value / sumAll;
			probResult.put(result, prob);
		}
//
//		probResult.entrySet().stream().forEach(p -> {
//			System.out.println(p.getKey() + " : " + p.getValue());
//		});

		return probResult;
	}

	public static List<MapAttributResult> countAttrs(List<DataSet> dataSets, List<Attribute> attrs,
			Map<String, Integer> countResults) {
//		Map<String, Long> map = new HashMap<String, Long>();
		List<MapAttributResult> list = new ArrayList<MapAttributResult>();
		List<String> results = new ArrayList<String>();
		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("result")).map(q -> q.getValues()).forEach(r -> {
			results.addAll(r);
		});

		List<Attribute> filterAttr = attrs.stream().filter(p -> !p.getName().equalsIgnoreCase("result"))
				.collect(Collectors.toList());

		for (Attribute attr : filterAttr) {
			for (String result : results) {
				int countResult = countResults.get(result);
				for (String attrValue : attr.getValues()) {
					long countAttr = dataSets.stream().filter(p -> {
						switch (attr.getName()) {
						case "weather":
							return p.getResult().equalsIgnoreCase(result) && p.getWeather().equalsIgnoreCase(attrValue);
						case "car":
							return p.getResult().equalsIgnoreCase(result) && p.getCar().equalsIgnoreCase(attrValue);
						default:
							return false;
						}
					}).count();
					Double probAttr = (double) countAttr / (countResult);
					MapAttributResult mar = new MapAttributResult(attr.getName(), attrValue, result, countAttr, probAttr);
					list.add(mar);
				}
			}
		}

//		list.stream().forEach(p -> {
//			System.out.println(
//					p.getAttribute() + " " + p.getResult() + " count: " + p.getCount() + " prob: " + p.getProb());
//		});

		return list;
	}

	public static Result prediction(List<Request> requests, List<Attribute> attrs, List<MapAttributResult> mapAttrs,
			Map<String, Double> probResults) {
		List<String> resultAttrs = new ArrayList();

		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("result")).map(m -> m.getValues()).forEach(q -> {
			resultAttrs.addAll(q);
		});

		List<Result> results = new ArrayList();
		for (String resultAttr : resultAttrs) {
			double prob = probResults.get(resultAttr);
			for (MapAttributResult mapAttr : mapAttrs) {
				if (resultAttr.equalsIgnoreCase(mapAttr.getStatus())) {
					for (Request request : requests) {
						if (request.getValueAttribute().equalsIgnoreCase(mapAttr.getValueAttriute())) {
							prob *= mapAttr.getProb();
						}
					}
				}
			}
			Result result = new Result(resultAttr, prob);
			results.add(result);
		}

//		mapAttrs.stream().forEach(p -> {
//			System.out.println(p.toString());
//		});
//
//		results.stream().forEach(p -> {
//			System.out.println(p.getName() + ": " + p.getValue());
//		});

		Result max = results.stream().max(Comparator.comparing(Result::getValue)).orElse(null);
		
		return max;
	}

}

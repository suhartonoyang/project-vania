package com.project.bayes.service;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import com.project.bayes.bean.MapAttributStatus;
import com.project.bayes.bean.Request;
import com.project.bayes.bean.Result;
import com.project.bayes.model.DataBayes;
import com.project.bayes.bean.Attribute;
import com.project.bayes.bean.DataSet;

@Service
public class BayesNaiveService {

	@Autowired
	private DataBayesService dataBayesService;

	public Result run(List<Request> requests) {
		// TODO code application logic here

		// generate data from database
		List<DataBayes> dataBayesList = generateData();

		// process data to count total value attribute and prob
		List<Attribute> attrs = getValueAttrs(dataBayesList);
		Map<String, Integer> mapCountStatus = countStatus(dataBayesList);
		Map<String, Double> mapProbResult = probStatus(mapCountStatus, attrs);
		List<MapAttributStatus> mapAttributeStatus = countAttrs(dataBayesList, attrs, mapCountStatus);
		Result resultPrediction = prediction(requests, attrs, mapAttributeStatus, mapProbResult, mapCountStatus);

//		System.out.println("kesimpulan: " + resultPrediction.getName());

		return resultPrediction;
	}

	private List<DataBayes> generateData() {
		List<DataBayes> dataBayesList = dataBayesService.getDataBayesAll();

//		dataBayesList.stream().forEach(p -> {
//			System.out.println(p.toString());
//		});

		return dataBayesList;

	}

	private List<Attribute> getValueAttrs(List<DataBayes> dataBayesList) {
		List<Attribute> attrs = new ArrayList<Attribute>();
		Field[] fields = DataBayes.class.getDeclaredFields();
		for (Field field : fields) {
			List<String> valueAttrs = new ArrayList<>();
			if (!field.getName().equalsIgnoreCase("id")) {
				dataBayesList.stream().map(p -> {
					switch (field.getName()) {
					case "jenisKelamin":
						return p.getJenisKelamin();
					case "jamuran":
						return p.getJamuran();
					case "gatalGatal":
						return p.getGatalGatal();
					case "rontok":
						return p.getRontok();
					case "garukGarukTelinga":
						return p.getGarukGarukTelinga();
					case "kropeng":
						return p.getKropeng();
					case "nafsuMakan":
						return p.getNafsuMakan();
					case "status":
						return p.getStatus();
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

	private Map<String, Integer> countStatus(List<DataBayes> dataBayesList) {
		List<DataBayes> temp = dataBayesList;
		Map<String, Integer> map = new HashMap<String, Integer>();
		temp.stream().map(p -> p.getStatus()).distinct().forEach(e -> {
			int hitung = dataBayesList.stream().filter(p -> p.getStatus().equals(e.toString()))
					.collect(Collectors.toList()).size();
			map.put(e, hitung);
		});

//		map.entrySet().forEach(p -> {
//			System.out.println("Jumlah Kelas " + p.getKey() + ": " + p.getValue());
//		});

		return map;
	}

	private Map<String, Double> probStatus(Map<String, Integer> countStatus, List<Attribute> attrs) {
		Map<String, Double> probStatus = new HashMap<String, Double>();
		List<String> statuses = new ArrayList<String>();
		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("status")).map(q -> q.getValues()).forEach(r -> {
			statuses.addAll(r);
		});

		for (String status : statuses) {
			int sumAll = countStatus.values().stream().mapToInt(Integer::intValue).sum();
			int value = countStatus.get(status);
			double prob = (double) value / sumAll;
			probStatus.put(status, prob);
		}

//		probStatus.entrySet().stream().forEach(p -> {
//			System.out.println(p.getKey() + " : " + p.getValue());
//		});

		return probStatus;
	}

	public static List<MapAttributStatus> countAttrs(List<DataBayes> dataBayesList, List<Attribute> attrs,
			Map<String, Integer> countStatus) {
		List<MapAttributStatus> list = new ArrayList<MapAttributStatus>();
		List<String> statuses = new ArrayList<String>();
		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("status")).map(q -> q.getValues()).forEach(r -> {
			statuses.addAll(r);
		});

		List<Attribute> filterAttr = attrs.stream().filter(p -> !p.getName().equalsIgnoreCase("status"))
				.collect(Collectors.toList());

		for (Attribute attr : filterAttr) {
			for (String status : statuses) {
				int countResult = countStatus.get(status);
				for (String attrValue : attr.getValues()) {
					long countAttr = dataBayesList.stream().filter(p -> {
						switch (attr.getName()) {
						case "jenisKelamin":
							return p.getStatus().equalsIgnoreCase(status)
									&& p.getJenisKelamin().equalsIgnoreCase(attrValue);
						case "jamuran":
							return p.getStatus().equalsIgnoreCase(status) && p.getJamuran().equalsIgnoreCase(attrValue);
						case "gatalGatal":
							return p.getStatus().equalsIgnoreCase(status)
									&& p.getGatalGatal().equalsIgnoreCase(attrValue);
						case "rontok":
							return p.getStatus().equalsIgnoreCase(status) && p.getRontok().equalsIgnoreCase(attrValue);
						case "garukGarukTelinga":
							return p.getStatus().equalsIgnoreCase(status)
									&& p.getGarukGarukTelinga().equalsIgnoreCase(attrValue);
						case "kropeng":
							return p.getStatus().equalsIgnoreCase(status) && p.getKropeng().equalsIgnoreCase(attrValue);
						case "nafsuMakan":
							return p.getStatus().equalsIgnoreCase(status)
									&& p.getNafsuMakan().equalsIgnoreCase(attrValue);
						default:
							return false;
						}
					}).count();
					Double probAttr = (double) countAttr / (countResult);
					MapAttributStatus mar = new MapAttributStatus(attr.getName(), attrValue, status, countAttr,
							probAttr);
					list.add(mar);
				}
			}
		}

//		list.stream().forEach(p -> {
//			System.out.println(
//					p.getAttribute() + " " + p.getStatus() + " count: " + p.getCount() + " prob: " + p.getProb());
//		});

		return list;
	}

	public Result prediction(List<Request> requests, List<Attribute> attrs, List<MapAttributStatus> mapAttrs,
			Map<String, Double> probStatus, Map<String, Integer> countStatus) {
		List<String> statusAttrs = new ArrayList();

		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("status")).map(m -> m.getValues()).forEach(q -> {
			statusAttrs.addAll(q);
		});

		List<Result> results = new ArrayList();
		for (String statusAttr : statusAttrs) {
			double prob = probStatus.get(statusAttr);
//			System.out.println("prob " + statusAttr + ": " + prob);
			for (MapAttributStatus mapAttr : mapAttrs) {
				if (statusAttr.equalsIgnoreCase(mapAttr.getStatus())) {
					for (Request request : requests) {
						if (request.getNameAttribute().equalsIgnoreCase(mapAttr.getNameAttribute())) {
							if (request.getValueAttribute().equalsIgnoreCase(mapAttr.getValueAttriute())) {
								boolean checkZero = requests.stream().filter(p->{
									return mapAttrs.stream().filter(q->{
										return  p.getNameAttribute().equalsIgnoreCase(q.getNameAttribute())
												&& p.getValueAttribute().equalsIgnoreCase(q.getValueAttriute())
												&& q.getCount()==0 && q.getStatus().equalsIgnoreCase(statusAttr);
									}).findAny().isPresent();
								}).findAny().isPresent();
//								System.out.println("checkZero: " + checkZero);
								if (checkZero) {
//									System.out.println("mapAttr: " + mapAttr.toString());
									long countAttr = mapAttr.getCount();
									++countAttr;
									int sumCountStatus = countStatus.values().stream().mapToInt(Integer::intValue)
											.sum();
									double newProb = (double) countAttr / sumCountStatus;
									prob *= newProb;
//									System.out.println("countAttr: " + countAttr + " - sumCountStatus: " + sumCountStatus + " - newProb: " + newProb);
								} else {
									prob *= mapAttr.getProb();
								}
							}
						}
					}
				}
			}
			Result result = new Result(statusAttr, prob);
			results.add(result);
		}

		mapAttrs.stream().forEach(p -> {
			System.out.println(p.toString());
		});

		results.stream().forEach(p -> {
			System.out.println(p.getName() + ": " + p.getValue());
		});

		Result max = results.stream().max(Comparator.comparing(Result::getValue)).orElse(null);

		return max;
	}
}

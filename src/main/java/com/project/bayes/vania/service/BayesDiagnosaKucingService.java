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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import com.project.bayes.vania.bean.Attribute;
import com.project.bayes.vania.bean.DataSet;
import com.project.bayes.vania.bean.MapAttributResult;
import com.project.bayes.vania.bean.Request;
import com.project.bayes.vania.bean.Result;
import com.project.bayes.vania.model.DataBayes;
import com.project.bayes.vania.model.DataLatihAnjing;
import com.project.bayes.vania.model.DataLatihKucing;
import com.project.bayes.vania.model.DataTestingAnjing;
import com.project.bayes.vania.model.DataTestingKucing;

@Service
public class BayesDiagnosaKucingService {

	@Autowired
	private DataLatihKucingService dataLatihKucingService;

	@Autowired
	private DataTestingKucingService dataTestingKucingService;

	public DataTestingKucing run(List<Request> requests) {
		// TODO code application logic here

		// generate data from database
		List<DataLatihKucing> dataBayesList = generateData();

		// process data to count total value attribute and prob
		List<Attribute> attrs = getValueAttrs(dataBayesList);
		Map<String, Integer> mapCountResults = countResults(dataBayesList);
		Map<String, Double> mapProbResults = probResults(mapCountResults, attrs);
		List<MapAttributResult> mapAttributeResults = countAttrs(dataBayesList, attrs, mapCountResults);
		Result resultPrediction = prediction(requests, attrs, mapAttributeResults, mapProbResults, mapCountResults);

//		System.out.println("kesimpulan: " + resultPrediction.getName());

		DataTestingKucing newData = new DataTestingKucing();
		requests.stream().forEach(p -> {
			switch (p.getNameAttribute()) {
			case "jenisKelamin":
				newData.setJenisKelamin(p.getValueAttribute());
			case "gatalGatal":
				newData.setGatalGatal(p.getValueAttribute());
			case "kulitKemerahan":
				newData.setKulitKemerahan(p.getValueAttribute());
			case "buluRontok":
				newData.setBuluRontok(p.getValueAttribute());
			case "kulitKering":
				newData.setKulitKering(p.getValueAttribute());
			case "bengkak":
				newData.setBengkak(p.getValueAttribute());
			case "kropeng":
				newData.setKropeng(p.getValueAttribute());
			}
		});

		newData.setResult(resultPrediction.getName());
		dataTestingKucingService.saveData(newData);

		return newData;
	}

	private List<DataLatihKucing> generateData() {
		List<DataLatihKucing> dataBayesList = dataLatihKucingService.getDataLatihKucingAll();

//		dataBayesList.stream().forEach(p -> {
//			System.out.println(p.toString());
//		});

		return dataBayesList;

	}

	private List<Attribute> getValueAttrs(List<DataLatihKucing> dataBayesList) {
		List<Attribute> attrs = new ArrayList<Attribute>();
		Field[] fields = DataLatihKucing.class.getDeclaredFields();
		for (Field field : fields) {
			List<String> valueAttrs = new ArrayList<>();
			if (!field.getName().equalsIgnoreCase("id")) {
				dataBayesList.stream().map(p -> {
					switch (field.getName()) {
					case "jenisKelamin":
						return p.getJenisKelamin();
					case "gatalGatal":
						return p.getGatalGatal();
					case "kulitKemerahan":
						return p.getKulitKemerahan();
					case "buluRontok":
						return p.getBuluRontok();
					case "kulitKering":
						return p.getKulitKering();
					case "bengkak":
						return p.getBengkak();
					case "kropeng":
						return p.getKropeng();
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

	private Map<String, Integer> countResults(List<DataLatihKucing> dataBayesList) {
		List<DataLatihKucing> temp = dataBayesList;
		Map<String, Integer> map = new HashMap<String, Integer>();
		temp.stream().map(p -> p.getResult()).distinct().forEach(e -> {
			int hitung = dataBayesList.stream().filter(p -> p.getResult().equals(e.toString()))
					.collect(Collectors.toList()).size();
			map.put(e, hitung);
		});

//		map.entrySet().forEach(p -> {
//			System.out.println("Jumlah Kelas " + p.getKey() + ": " + p.getValue());
//		});

		return map;
	}

	private Map<String, Double> probResults(Map<String, Integer> countResults, List<Attribute> attrs) {
		Map<String, Double> probResults = new HashMap<String, Double>();
		List<String> results = new ArrayList<String>();
		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("result")).map(q -> q.getValues()).forEach(r -> {
			results.addAll(r);
		});

		for (String result : results) {
			int sumAll = countResults.values().stream().mapToInt(Integer::intValue).sum();
			int value = countResults.get(result);
			double prob = (double) value / sumAll;
			probResults.put(result, prob);
		}

//		probStatus.entrySet().stream().forEach(p -> {
//			System.out.println(p.getKey() + " : " + p.getValue());
//		});

		return probResults;
	}

	public static List<MapAttributResult> countAttrs(List<DataLatihKucing> dataBayesList, List<Attribute> attrs,
			Map<String, Integer> countResults) {
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
					long countAttr = dataBayesList.stream().filter(p -> {
						switch (attr.getName()) {
						case "jenisKelamin":
							return p.getResult().equalsIgnoreCase(result)
									&& p.getJenisKelamin().equalsIgnoreCase(attrValue);
						case "gatalGatal":
							return p.getResult().equalsIgnoreCase(result)
									&& p.getGatalGatal().equalsIgnoreCase(attrValue);
						case "kulitKemerahan":
							return p.getResult().equalsIgnoreCase(result)
									&& p.getKulitKemerahan().equalsIgnoreCase(attrValue);
						case "buluRontok":
							return p.getResult().equalsIgnoreCase(result)
									&& p.getBuluRontok().equalsIgnoreCase(attrValue);
						case "kulitKering":
							return p.getResult().equalsIgnoreCase(result)
									&& p.getKulitKering().equalsIgnoreCase(attrValue);
						case "bengkak":
							return p.getResult().equalsIgnoreCase(result) && p.getBengkak().equalsIgnoreCase(attrValue);
						case "kropeng":
							return p.getResult().equalsIgnoreCase(result) && p.getKropeng().equalsIgnoreCase(attrValue);
						default:
							return false;
						}
					}).count();
					Double probAttr = (double) countAttr / (countResult);
					MapAttributResult mar = new MapAttributResult(attr.getName(), attrValue, result, countAttr,
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

	public Result prediction(List<Request> requests, List<Attribute> attrs, List<MapAttributResult> mapAttrs,
			Map<String, Double> probResults, Map<String, Integer> countResults) {
		List<String> resultAttrs = new ArrayList();

		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("result")).map(m -> m.getValues()).forEach(q -> {
			resultAttrs.addAll(q);
		});

		List<Result> results = new ArrayList();
		for (String resultAttr : resultAttrs) {
			double prob = probResults.get(resultAttr);
//			System.out.println("prob " + resultAttr + ": " + prob);
			for (MapAttributResult mapAttr : mapAttrs) {
				if (resultAttr.equalsIgnoreCase(mapAttr.getStatus())) {
					for (Request request : requests) {
						if (request.getNameAttribute().equalsIgnoreCase(mapAttr.getNameAttribute())) {
							if (request.getValueAttribute().equalsIgnoreCase(mapAttr.getValueAttriute())) {
								boolean checkZero = requests.stream().filter(p -> {
									return mapAttrs.stream().filter(q -> {
										return p.getNameAttribute().equalsIgnoreCase(q.getNameAttribute())
												&& p.getValueAttribute().equalsIgnoreCase(q.getValueAttriute())
												&& q.getCount() == 0 && q.getStatus().equalsIgnoreCase(resultAttr);
									}).findAny().isPresent();
								}).findAny().isPresent();
//								System.out.println("checkZero: " + checkZero);
								if (checkZero) {
//									System.out.println("mapAttr: " + mapAttr.toString());
									long countAttr = mapAttr.getCount();
									++countAttr;
									int sumCountStatus = countResults.values().stream().mapToInt(Integer::intValue)
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
			Result result = new Result(resultAttr, prob);
			results.add(result);
		}

//		mapAttrs.stream().forEach(p -> {
//			System.out.println(p.toString());
//		});

//		results.stream().forEach(p -> {
//			System.out.println(p.getName() + ": " + p.getValue());
//		});

		Result max = results.stream().max(Comparator.comparing(Result::getValue)).orElse(null);

		return max;
	}
}

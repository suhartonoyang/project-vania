package com.project.bayes.vania.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bayes.vania.bean.Attribute;
import com.project.bayes.vania.bean.MapAttributResult;
import com.project.bayes.vania.bean.Request;
import com.project.bayes.vania.bean.Result;
import com.project.bayes.vania.model.DataLatihKucing;
import com.project.bayes.vania.model.DataTestingKucing;

@Service
public class BayesDiagnosaKucingService {

	@Autowired
	private DataLatihKucingService dataLatihKucingService;

	@Autowired
	private DataTestingKucingService dataTestingKucingService;

	public Map<String, Object> run(List<Request> requests) {
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

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("dataTestingKucing", newData);
		returnMap.put("resultJourney", resultPrediction.getJourney());

		return returnMap;
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
			int hitung = dataBayesList.stream().filter(p -> p.getResult().equals(e.toString())).collect(Collectors.toList()).size();
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

		List<Attribute> filterAttr = attrs.stream().filter(p -> !p.getName().equalsIgnoreCase("result")).collect(Collectors.toList());

		for (Attribute attr : filterAttr) {
			for (String result : results) {
				int countResult = countResults.get(result);
				for (String attrValue : attr.getValues()) {
					long countAttr = dataBayesList.stream().filter(p -> {
						switch (attr.getName()) {
						case "jenisKelamin":
							return p.getResult().equalsIgnoreCase(result) && p.getJenisKelamin().equalsIgnoreCase(attrValue);
						case "gatalGatal":
							return p.getResult().equalsIgnoreCase(result) && p.getGatalGatal().equalsIgnoreCase(attrValue);
						case "kulitKemerahan":
							return p.getResult().equalsIgnoreCase(result) && p.getKulitKemerahan().equalsIgnoreCase(attrValue);
						case "buluRontok":
							return p.getResult().equalsIgnoreCase(result) && p.getBuluRontok().equalsIgnoreCase(attrValue);
						case "kulitKering":
							return p.getResult().equalsIgnoreCase(result) && p.getKulitKering().equalsIgnoreCase(attrValue);
						case "bengkak":
							return p.getResult().equalsIgnoreCase(result) && p.getBengkak().equalsIgnoreCase(attrValue);
						case "kropeng":
							return p.getResult().equalsIgnoreCase(result) && p.getKropeng().equalsIgnoreCase(attrValue);
						default:
							return false;
						}
					}).count();
					Double probAttr = (double) countAttr / (countResult);
					MapAttributResult mar = new MapAttributResult(attr.getName(), attrValue, result, countAttr, probAttr, false, false, 0,
							0);
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
		List<String> resultAttrs = new ArrayList<String>();

		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("result")).map(m -> m.getValues()).forEach(q -> {
			resultAttrs.addAll(q);
		});

		List<Result> results = new ArrayList<Result>();
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
												&& p.getValueAttribute().equalsIgnoreCase(q.getValueAttriute()) && q.getCount() == 0
												&& q.getStatus().equalsIgnoreCase(resultAttr);
									}).findAny().isPresent();
								}).findAny().isPresent();
								mapAttr.setSelected(true);
//								System.out.println("mapAttr: " + mapAttr);
//								System.out.println("checkZero: " + checkZero);
								if (checkZero) {
									long countAttr = mapAttr.getCount();
									++countAttr;
									int sumCountStatus = countResults.values().stream().mapToInt(Integer::intValue).sum();
									double newProb = (double) countAttr / sumCountStatus;
									prob *= newProb;

									mapAttr.setZero(true);
									mapAttr.setNewCount(countAttr);
									mapAttr.setNewProb(prob);

//									System.out.println("countAttr: " + countAttr + " - sumCountStatus: " + sumCountStatus + " - newProb: " + newProb);
								} else {
									prob *= mapAttr.getProb();
								}
							}
						}
					}
				}
			}
			Result result = new Result(resultAttr, prob, null);
			results.add(result);
		}

//		mapAttrs.stream().forEach(p -> {
//			System.out.println(p.toString());
//		});

		results.stream().forEach(p -> {
			setResultJourney(mapAttrs, results, countResults, probResults);
		});

		Result max = results.stream().max(Comparator.comparing(Result::getValue)).orElse(null);

		return max;
	}

	private void setResultJourney(List<MapAttributResult> mapAttrs, List<Result> results, Map<String, Integer> countResults,
			Map<String, Double> probResults) {
		int sumCountStatus = countResults.values().stream().mapToInt(Integer::intValue).sum();
		for (Result r : results) {
			int countResult = countResults.get(r.getName());
//			System.out.println("=================" + r.getName() + "=================");
			String print = null;
			String printZero = null;
			String calculation = null;
			String calculationZero = null;
			String prob = "(" + countResult + "/" + sumCountStatus + ") = " + probResults.get(r.getName());
			for (MapAttributResult q : mapAttrs) {
				if (q.isSelected() && q.getStatus().equalsIgnoreCase(r.getName())) {
					String attr = q.getNameAttribute();
					String calcProb = "(" + q.getCount() + "/" + countResult + ") = " + q.getProb();
					print = (print == null) ? attr + "-" + calcProb : print + ", " + attr + "-" + calcProb;
					calculation = (calculation == null)
							? prob.substring(0, prob.lastIndexOf('=')).trim() + " x " + calcProb.substring(0, calcProb.lastIndexOf('=')).trim()
							: calculation + " x " + calcProb.substring(0, calcProb.lastIndexOf('=')).trim();
					if (q.isZero()) {
						String calcProbZero = "(" + q.getNewCount() + "/" + sumCountStatus + ") = " + q.getNewProb();
						printZero = (printZero == null) ? attr + "-" + calcProbZero : printZero + ", " + attr + "-" + calcProbZero;
						calculationZero = (calculationZero == null)
								? prob.substring(0, prob.lastIndexOf('=')).trim() + " x "
										+ calcProbZero.substring(0, calcProbZero.lastIndexOf('=')).trim()
								: calculationZero + " x " + calcProbZero.substring(0, calcProbZero.lastIndexOf('=')).trim();
					}
				}
			}

//			System.out.println("Prob Value: " + prob);
//			System.out.println("Origin Journey: " + print);
//			System.out.println("Origin Journey Calculation: " + calculation);
//			System.out.println("Journey because of Laplace Correction: " + printZero);
//			System.out.println("Journey Calculation because of Laplace Correction: " + calculationZero);

			String journey = "=================" + r.getName() + "=================\n";
			journey += "Prob Value: " + prob + "\n";
			journey += "Origin Journey: " + print + "\n";
			journey += "Origin Journey Calculation: " + calculation;
			if (printZero != null) {
				journey += "\n";
				journey += "Journey because of Laplace Correction: " + printZero + "\n";
				journey += "Journey Calculation because of Laplace Correction: " + calculationZero;
			}
			r.setJourney(journey);

//			System.out.println(journey);
		}
	}
}

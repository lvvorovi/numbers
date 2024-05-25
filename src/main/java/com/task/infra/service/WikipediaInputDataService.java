package com.task.infra.service;

import com.task.core.dto.CodeDto;
import com.task.core.service.InputDataService;
import com.task.infra.properties.WikiProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class WikipediaInputDataService implements InputDataService {

    private final WikiProperties properties;

    @Override
    public List<CodeDto> fetchAll() throws IOException {
        var result = new TreeMap<Integer, String>();

        Jsoup.connect(properties.getUrl())
                .get()
                .body()
                .getElementsByClass("wikitable sortable sticky-header-multi")
                .select("tr")
                .stream()
                .map(tr -> tr.select("td"))
                .filter(tdList -> tdList.size() == 4)
                .map(this::toTableRow)
                .forEach(tableRow -> tableRow.getCodeList()
                        .forEach(code -> {
                            if (result.containsKey(code)) {
                                result.put(code, tableRow.getCountryName()
                                        .concat(", ")
                                        .concat(result.get(code)));
                            } else {
                                result.put(code, tableRow.getCountryName());
                            }
                        }));

        log.info("{} fetched {} unique codes", this.getClass().getSimpleName(), result.size());

        return result
                .descendingMap()
                .entrySet()
                .stream()
                .map(entrySet -> new CodeDto(
                        entrySet.getKey(),
                        entrySet.getValue()))
                .toList();
    }

    private TableRow toTableRow(Elements tdList) {
        var codeListString = getSanitizeCodeString(tdList);

        List<String> regionList = new ArrayList<>();
        List<String> codeList;
        if (containsRegions(codeListString)) {
            regionList = Arrays.stream(codeListString.substring(
                                    codeListString.indexOf("(") + 1,
                                    codeListString.indexOf(")"))
                            .split(","))
                    .toList();

            codeList = Arrays.stream(codeListString.substring(0, codeListString.indexOf("("))
                            .split(","))
                    .toList();
        } else {
            codeList = Arrays.stream(codeListString.split(","))
                    .toList();
        }

        var tableRow = new TableRow(tdList.get(0).text());

        for (var code : codeList) {
            if (!regionList.isEmpty()) {

                for (var region : regionList) {
                    tableRow.getCodeList().add(Integer.valueOf(code.concat(region)));
                }
            } else {
                tableRow.getCodeList().add(Integer.valueOf(code));
            }
        }

        return tableRow;
    }

    private String getSanitizeCodeString(Elements tdList) {
        return tdList.get(1).text().replace(" ", "");
    }

    private boolean containsRegions(String codeListString) {
        return codeListString.contains("(");
    }

    @Data
    static class TableRow {
        String countryName;
        List<Integer> codeList;

        public TableRow(String countryName) {
            this.countryName = countryName;
            this.codeList = new ArrayList<>();
        }
    }
}

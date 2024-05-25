package com.task.core.config;

import com.task.core.service.CodeService;
import com.task.core.service.InputDataService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class LoadCodesConfig {

    private final InputDataService inputDataService;
    private final CodeService codeService;

    @PostConstruct
    @Transactional
    public void loadData() {
        try {
            var codeDtoList = inputDataService.fetchAll();
            codeService.deleteAll();
            codeService.saveAll(codeDtoList);
        } catch (IOException e) {
            throw new IllegalStateException("Exception while loading data", e);
        }
    }

}

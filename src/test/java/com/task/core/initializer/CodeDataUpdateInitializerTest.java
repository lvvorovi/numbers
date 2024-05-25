package com.task.core.initializer;

import com.task.core.service.CodeService;
import com.task.core.service.InputDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CodeDataUpdateInitializerTest {

    @Mock
    private InputDataService inputDataService;
    @Mock
    private CodeService codeService;

    @InjectMocks
    private CodeDataUpdateInitializer victim;

    @Test
    void init() throws IOException {
        when(inputDataService.fetchAll()).thenReturn(List.of());
        doNothing().when(codeService).deleteAll();
        doNothing().when(codeService).saveAll(List.of());

        var inOrder = Mockito.inOrder(inputDataService, codeService);

        victim.init();

        inOrder.verify(inputDataService).fetchAll();
        inOrder.verify(codeService).deleteAll();
        inOrder.verify(codeService).saveAll(List.of());
    }

    @Test
    void init_whenCaughtIOException_thenThrowIllegalStateException() throws IOException {
        when(inputDataService.fetchAll()).thenThrow(new IOException());

        assertThatThrownBy(() -> victim.init())
                .isInstanceOf(IllegalStateException.class);
    }

}

package com.task.core.service;

import com.task.core.dto.CodeDto;

import java.io.IOException;
import java.util.List;

public interface InputDataService {

    List<CodeDto> fetchAll() throws IOException;

}

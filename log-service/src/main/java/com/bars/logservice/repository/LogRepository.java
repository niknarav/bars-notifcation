package com.bars.logservice.repository;

import com.bars.logservice.model.LogMessage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<LogMessage, String> {
}

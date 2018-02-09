/*
 * (C) Copyright 2017-2019 ElasTest (http://elastest.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.elastest.eus.service;

import static java.lang.invoke.MethodHandles.lookup;
import static net.thisptr.jackson.jq.JsonQuery.compile;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.thisptr.jackson.jq.JsonQuery;

/**
 * Service wrapper for JQ (lightweight command-line JSON processor).
 *
 * @author Boni Garcia (boni.garcia@urjc.es)
 * @since 0.5.1
 * @see <a href=
 *      "https://stedolan.github.io/jq/">https://stedolan.github.io/jq/</a>
 */
@Service
public class JqService {

    final Logger log = getLogger(lookup().lookupClass());

    public String processJsonWithJq(String json, String jq) throws IOException {
        log.debug("JSON message before processing: {}", json);
        log.debug("jq command: {}", jq);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode input = objectMapper.readTree(json);
        JsonQuery jsonQuery = compile(jq);
        String result = jsonQuery.apply(input).iterator().next().toString();
        log.debug("JSON message after processing: {}", result);
        return result;
    }

}

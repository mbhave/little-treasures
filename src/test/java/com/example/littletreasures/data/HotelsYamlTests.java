/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.littletreasures.data;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import org.springframework.core.io.ByteArrayResource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link HotelsYaml}.
 */
class HotelsYamlTests {

	private static final String yaml = """
			file-version: 1
			hotels:
			- name: flamingo
			  address: 3555 Las Vegas Boulevard South
			  image: Flamingohotelyay.jpg
			  rooms: 3642
			  opened: "1946"
			  operator: Caesars Entertainment
			  theme: Art Deco, Miami
			  architects:
			  - Richard R. Stadelman
			  geographic-order: Center strip""";

	@Test
	void testName() {
		ByteArrayResource resource = new ByteArrayResource(yaml.getBytes(StandardCharsets.UTF_8));
		Properties properties = HotelsYaml.loadAsProperties(resource);
		Map<String, Object> expected = new HashMap<>();
		expected.put("file-version", 1);
		expected.put("hotels[0].name", "flamingo");
		expected.put("hotels[0].address", "3555 Las Vegas Boulevard South");
		expected.put("hotels[0].image", "Flamingohotelyay.jpg");
		expected.put("hotels[0].rooms", 3642);
		expected.put("hotels[0].opened", "1946");
		expected.put("hotels[0].operator", "Caesars Entertainment");
		expected.put("hotels[0].theme", "Art Deco, Miami");
		expected.put("hotels[0].architects[0]", "Richard R. Stadelman");
		expected.put("hotels[0].geographic-order", "Center strip");
		assertThat(properties).containsExactlyInAnyOrderEntriesOf(expected);
	}

}

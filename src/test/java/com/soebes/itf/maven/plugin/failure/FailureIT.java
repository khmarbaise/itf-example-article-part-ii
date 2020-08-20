package com.soebes.itf.maven.plugin.failure;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

@MavenJupiterExtension
class FailureIT {

  @MavenTest
  @DisplayName("The basic configuration should result in a successful build.")
  void basic_configuration(MavenExecutionResult project) {
    assertThat(project).isSuccessful();
  }

  @MavenTest
  void basic_configuration_checking_logout(MavenExecutionResult result) {
    assertThat(result)
        .isSuccessful()
        .out()
        .info()
        .containsSubsequence(
            "--- maven-enforcer-plugin:3.0.0-M1:enforce (enforce-maven) @ basic_configuration_checking_logout ---",
            "--- jacoco-maven-plugin:0.8.5:prepare-agent (default) @ basic_configuration_checking_logout ---",
            "--- maven-resources-plugin:3.1.0:resources (default-resources) @ basic_configuration_checking_logout ---",
            "--- maven-compiler-plugin:3.8.1:compile (default-compile) @ basic_configuration_checking_logout ---",
            "--- maven-resources-plugin:3.1.0:testResources (default-testResources) @ basic_configuration_checking_logout ---",
            "--- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ basic_configuration_checking_logout ---",
            "--- maven-surefire-plugin:3.0.0-M4:test (default-test) @ basic_configuration_checking_logout ---",
            "--- maven-jar-plugin:3.2.0:jar (default-jar) @ basic_configuration_checking_logout ---",
            "--- maven-site-plugin:3.9.1:attach-descriptor (attach-descriptor) @ basic_configuration_checking_logout ---"
        );
    assertThat(result)
        .isSuccessful()
        .out()
        .warn()
        .contains("JAR will be empty - no content was marked for inclusion!");

  }
}

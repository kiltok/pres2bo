/**
 * Copyright (C) 2016 Czech Technical University in Prague
 * <p>
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details. You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package cz.cvut.kotlito1.wpa.pres.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "cz.cvut.kotlito1.wpa.pres.service")
public class ServiceConfig {

    /**
     * {@link RestTemplate} can be used to communicate with web services of another application -
     * see for example <a href="http://www.baeldung.com/rest-template">http://www.baeldung.com/rest-template</a>.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

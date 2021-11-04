package com.github.satyam2004.loganalyser.repository;

import com.github.satyam2004.loganalyser.model.EventType;
import com.github.satyam2004.loganalyser.model.persistence.Alert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class AlertRepositoryTest {
    @Autowired
    private AlertRepository repository;

    @Test
    public void whenFindingCustomerById_thenCorrect() {
        Alert alert = new Alert();
        alert.setId("alert-1");
        alert.setDuration(3);
        alert.setHost("127.0.0.1");
        alert.setType(EventType.APPLICATION_LOG);

        repository.save(alert);
        assertThat(repository.findById("alert-1")).isInstanceOf(Optional.class);
    }

    @Test
    public void whenFindingAllCustomers_thenCorrect() {
        Alert alert1 = new Alert();
        alert1.setId("alert-1");
        alert1.setDuration(3);
        alert1.setHost("127.0.0.1");
        alert1.setType(EventType.APPLICATION_LOG);

        Alert alert2 = new Alert();
        alert2.setId("alert-2");
        alert2.setDuration(7);
        alert2.setHost(null);
        alert2.setType(null);
        alert2.setAlert(Boolean.TRUE);

        repository.save(alert1);
        repository.save(alert2);

        assertThat(repository.findAll()).isInstanceOf(List.class);
    }
}
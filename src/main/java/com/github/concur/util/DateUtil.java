package com.github.concur.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateUtil {

  public int dateDifference(Date a, Date b) {
    // https://stackoverflow.com/questions/1555262/calculating-the-difference-between-two-java-date-instances/
    LocalDate a1 =a.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate b1 =b.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return Math.abs(Period.between(a1, b1).getYears());
  }

}

package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {

  public static void main(String[] args) {
    /*// 获取当前日期
    LocalDate currentDate = LocalDate.now();

    // 创建格式化器，用于输出 yyyyMM 格式
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
    String fromMonth = currentDate.minusMonths(1).format(formatter);
    String toMonth = currentDate.minusMonths(12).format(formatter);

    System.out.println(fromMonth);
    System.out.println(toMonth);*/

    Stream<Integer> stream = Stream.of(1,2,3).peek(e -> System.out.println(e));
    long count = Stream.of(1,2,3).peek(e -> System.out.println(e)).count();

    System.out.println(count);

//    Stream.iterate(1, (x) -> x + 1).limit(100).sum();
  }

}

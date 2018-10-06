package vn.thai.scheduler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.thai.scheduler.config.SchedulerConfig;


@SpringBootApplication
public class Main implements CommandLineRunner {

  public static void main(String[] args) {
    new SpringApplication(Main.class, SchedulerConfig.class).run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("hello world");
  }
}

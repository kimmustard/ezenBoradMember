package ezen.toyBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MyBatisConfig.class)
@SpringBootApplication(scanBasePackages = "ezen.toyBoard.controller")
public class ToyBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToyBoardApplication.class, args);
	}

}

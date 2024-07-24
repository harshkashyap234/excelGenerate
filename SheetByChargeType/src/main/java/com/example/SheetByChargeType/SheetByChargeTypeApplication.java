package com.example.SheetByChargeType;
import com.example.SheetByChargeType.Service.ApiService;
import com.example.SheetByChargeType.Service.LoadJsonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class SheetByChargeTypeApplication implements CommandLineRunner {
	@Autowired
	private LoadJsonDataService loadJsonDataService;
	@Autowired
	private ApiService apiService;
	public static void main(String[] args) {
		SpringApplication.run(SheetByChargeTypeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		apiService.hitApiForOrgCode();
	}
//
//	@Override
//	public void run(String... args) throws Exception {
//			loadJsonDataService.readJsonFile();
//	}
}

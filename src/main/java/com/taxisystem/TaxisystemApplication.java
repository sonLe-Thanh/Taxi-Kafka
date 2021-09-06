package com.taxisystem;

import com.uber.h3core.H3Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@SpringBootApplication
public class TaxisystemApplication {

	public static void main(String[] args) throws IOException {
//		double max_lat = 10.963984;
//		double max_long = 106.477055;
//
//		double min_lat = 10.699614;
//		double min_long = 106.579939;
//		int res = 10;
//		int [] seat  = {2,4,7};
//
//		H3Core h3 = H3Core.newInstance();
//		try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {
//			int thres = 500000;
//			for (int i =0;i<thres;i++){
//				StringBuilder sb = new StringBuilder();
//				Random r = new Random();
//				double lat = min_lat + (max_lat - min_lat) * r.nextDouble();
//				double lon = min_long + (max_long - min_long) * r.nextDouble();
//				int random_seat_index = r.nextInt(seat.length);
//				int set = seat[random_seat_index];
//
//				String hexAddr = h3.geoToH3Address(lat, lon, res);
//				sb.append(i).append(",").append(hexAddr).append(",").append(lon).append(",").append(lat).append(",").append(set).append("\n");
//
//				writer.write(sb.toString());
//			}
//
//
//			System.out.println("done!");
//
//		} catch (FileNotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		SpringApplication.run(TaxisystemApplication.class, args);
	}

}

package ru.geekbrains.summer.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secret.properties")
public class SummerMarketApplication {
	// Домашнее задание:
	// 1. Исследовательская задача: прикрутить картинки к товарам

	// План по магазину:

	// Занятие 11:
	// - Swagger
	// - Платежная система (PayPal)
	// - Картинки

	// Занятие 12:
	// - KeyCloak
	// - Регистрация

	// - Рассылка писем
	// - Админка
	// - Промо-коды

	public static void main(String[] args) {
		SpringApplication.run(SummerMarketApplication.class, args);
	}
}

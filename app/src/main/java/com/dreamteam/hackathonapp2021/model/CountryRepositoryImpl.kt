package com.dreamteam.hackathonapp2021.model

import com.dreamteam.hackathonapp2021.data.CountriesDataSource
import com.dreamteam.hackathonapp2021.data.CountriesResult

class CountryRepositoryImpl(
    private val onlineDataSource: CountriesDataSource,
    private val localDataSource: CountriesDataSource,
) : CountryRepository {

    override suspend fun getCountries(callback: (Result) -> Unit) {
        val dataSource = if (localDataSource.hasData()) localDataSource else onlineDataSource
        dataSource.getCountries { result ->
            when (result) {
                is CountriesResult.Success -> {
                    val countries = result.features.map { feature ->
                        val properties = feature.properties
                        val restrictions = properties.restrictions
                        Country(
                            properties.countryId.toLong(), //"country_id": "29475336",
                            properties.countryName, //"country_name": "Австралия",
                            properties.countryCode, //"country_code": "AU",
                            //"master_travel_restrictions_translation": "Высокий",
                            //restrictions.masterTravelRestrictionsTranslation,
                            //"destination_self_isolation_translation": "Действует карантин",
                            //restrictions.destinationSelfIsolationTranslation,
                            //"entry_restrictions_translation": "Высокий",
                            //restrictions.entryRestrictionsTranslation,
                            //"destination_restrictions_commentary_translation": "1. Пассажирам запрещен въезд в Австралию.    \n  \n\t-  Это не относится к:  \n\t-  граждане Австралии и их ближайшие родственники;  \n\t-  постоянные жители Австралии и их ближайшие родственники;  \n\t-  граждане Новой Зеландии, проживающие в Австралии, и их ближайшие родственники;  \n\t-  пассажиры, прибывающие из Новой Зеландии, находившиеся в Новой Зеландии в течение последних 14 дней и путешествующие рейсом в Зоне безопасного путешествия;  \n\t-  пассажиры, у которых есть виза и которым было предоставлено освобождение перед вылетом; подробности можно найти на <https://travel-exemptions.homeaffairs.gov.au/tep> .  \n\t  \n2.  Пассажиры могут быть обязаны предъявить форму «Декларации путешествия» по прибытии или при транзите через Австралию. Форму можно получить по адресу <https://covid19.homeaffairs.gov.au/australia-travel-declaration> или по прибытии.  \n3.  Пассажиры могут быть помещены на 14-дневный карантин в первом пункте въезда. Подробности можно найти на <https://covid19.homeaffairs.gov.au/new-zealand-safe-travel-zone> .  \n4.  Экипаж авиакомпании подлежит самоизоляции до следующего полета.  \n5.  Пассажиры, следующие транзитом через Австралию на срок от 8 до 72 часов, подлежат карантину до следующего рейса.  \n6.  Пассажиры, въезжающие или следующие транзитом через Австралию, должны иметь медицинскую справку с отрицательным результатом ПЦР-теста на коронавирус (COVID-19). Тест должен быть сдан не позднее чем за 72 часа до отправления из первого пункта посадки. Более подробную информацию можно найти на [https://www.health.gov.au/news/health-alerts/novel-coronavirus-2019-ncov-health-alert/coronavirus-covid-19-restrictions/coronavirus-covid-19-. советы-международные-путешественники / часто задаваемые вопросы о коронавирусе-covid-19 для-международных-путешественников в австралию](https://www.health.gov.au/news/health-alerts/novel-coronavirus-2019-ncov-health-alert/coronavirus-covid-19-restrictions/coronavirus-covid-19-advice-for-international-travellers/coronavirus-covid-19-faqs-for-international-travellers-to-australia) .    \n  \n\t-  Это не относится к:  \n\t-  пассажиры младше 5 лет;  \n\t-  пассажиры, прибывающие из Кирибати, Новой Зеландии, Ниуэ, Самоа, Соломоновых островов, Токелау, Тонга, Тувалу или Вануату;  \n\t-  пассажиры с медицинской справкой с положительным результатом ПЦР-теста на коронавирус (COVID-19) и медицинской выпиской, выданной не менее чем за 4 недели до вылета, в которой указано, что:  \n\t-  прошло не менее 14 дней с момента заражения Коронавирусом (COVID-19); и  \n\t-  72 часа прошло с момента исчезновения лихорадки и респираторных симптомов; и  \n\t-  пассажиры вылечились от коронавируса (COVID-19) и не заразны.",
                            //restrictions.destinationRestrictionsCommentaryTranslation,
                        )
                    }
                    callback(Result.Success(countries))
                }
                is CountriesResult.Error -> {
                    callback(Result.Error(result.message))
                }
            }
        }
    }
}

CREATE TABLE `Weather` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `temperature` varchar(45) DEFAULT NULL,
  `feelsLike` varchar(45) DEFAULT NULL,
  `windSpeed` varchar(45) DEFAULT NULL,
  `savingTime` varchar(45) DEFAULT NULL,
  `cityName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



INSERT INTO `Weather` VALUES 
	(1,'clear sky','277.3','273.42','5','16:21:03.502311',"Shanghai"),
	(2,'broken clouds','283.33','282.68','3.13','16:27:48.437862','taipei');
	
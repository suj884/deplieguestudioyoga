-- MySQL dump 10.13  Distrib 9.3.0, for Win64 (x86_64)
--
-- Host: crossover.proxy.rlwy.net    Database: railway
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog_posts`
--

DROP TABLE IF EXISTS `blog_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_posts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text,
  `image_url` varchar(255) DEFAULT NULL,
  `published_date` date DEFAULT NULL,
  `summary` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_posts`
--

LOCK TABLES `blog_posts` WRITE;
/*!40000 ALTER TABLE `blog_posts` DISABLE KEYS */;
INSERT INTO `blog_posts` VALUES (1,'<p>En el espejo de la mente agitada, vemos solo reflejos distorsionados. Esta es la primera verdad. La segunda es que, al calmar la mente, la realidad se muestra clara y serena. En este artículo exploramos técnicas y consejos para aquietar los pensamientos y encontrar paz interior a través de la práctica regular de yoga y meditación.</p>','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748628243/yoga_En_el_espejo_de_la_mente_agitada_uduyzn.jpg','2025-05-01','En el espejo de la mente agitada, vemos solo reflejos distorsionados. Esta es la primera verdad. La segunda es que …','En el espejo de la mente agitada'),(2,'<p>Nuestras clases de Yoga Salud, dirigidas por la Dra. Irene Escames, traen a nuestro estudio una combinación excepcional: la precisión médica y la sabiduría ancestral del yoga. Descubre cómo esta disciplina puede ayudarte a mejorar tu bienestar físico y mental, con ejercicios adaptados a todas las edades y condiciones.</p>','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748593857/meditation-7895775_1280_utjwop.jpg','2025-04-20','Nuestras clases de Yoga Salud, dirigidas por la Dra. Irene Escames, traen a nuestro estudio una combinación excepcional: la precisión …','Yoga Salud con la Dra. Irene Escames'),(3,'<p>Hay una sabiduría en las manos que han sembrado décadas. Una memoria en las rodillas que conocen el peso de los años y la experiencia. Este post rinde homenaje a nuestros practicantes veteranos y explora cómo el yoga puede acompañarnos y enriquecernos a cualquier edad.</p>','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748628242/yoga__Sabidur%C3%ADa_en_las_manos_memoria_en_las_rodillas_yukvwh.jpg','2025-04-10','Hay una sabiduría en las manos que han sembrado décadas. Una memoria en las rodillas que conocen el peso de …','Sabiduría en las manos, memoria en las rodillas'),(4,'<p>La Arteterapia Humanista es mucho más que dibujar, pintar o moldear; es una forma de conectar contigo mismo a través de la creatividad. En este artículo te contamos cómo el arte puede ser un camino de autoconocimiento, sanación y expresión emocional, complementando la práctica de yoga y meditación.</p>','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748628246/arte_terapia_ak1djl.jpg','2025-03-30','La Arteterapia Humanista es mucho más que dibujar, pintar o moldear; es una forma de conectar contigo mismo a través …','Arteterapia Humanista: mucho más que dibujar'),(5,'<p>Nuestra visión del yoga, arte, creatividad, salud y bienestar es integradora y abierta. Creemos en una práctica que va más allá de lo físico, abarcando la mente, el espíritu y la expresión creativa. Te invitamos a descubrir nuestro enfoque y a sumarte a nuestra comunidad.</p>','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748593975/rocks-7354363_1280_qw5kli.jpg','2025-03-20','Nuestra visión del yoga, arte, creatividad, salud y bienestar.','Nuestra visión: yoga, arte, creatividad y bienestar');
/*!40000 ALTER TABLE `blog_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `capacity` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `event_date` date DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `instructor` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `time_end` time(6) DEFAULT NULL,
  `time_init` time(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (18,_binary '',20,'Clase de yoga para todos los niveles','2025-06-16','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494252/imagen_15_magxrm.png','Sara','Sala 1','11:00:00.000000','10:00:00.000000','Yoga Suave'),(19,_binary '',15,'Clase de yoga para todos los niveles','2025-06-16','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494245/imagen_2_jlo3al.png','Mariana','Sala 2','19:00:00.000000','18:00:00.000000','Yoga Terapeutico'),(20,_binary '',20,'Clase de yoga para todos los niveles','2025-06-16','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 3','21:00:00.000000','20:00:00.000000','Hatha Yoga'),(21,_binary '',20,'Clase de yoga para todos los niveles','2025-06-23','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494252/imagen_15_magxrm.png','Sara','Sala 1','11:00:00.000000','10:00:00.000000','Yoga Suave'),(22,_binary '',15,'Clase de yoga para todos los niveles','2025-06-23','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494245/imagen_2_jlo3al.png','Mariana','Sala 2','19:00:00.000000','18:00:00.000000','Yoga Terapeutico'),(23,_binary '',20,'Clase de yoga para todos los niveles','2025-06-23','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 1','21:00:00.000000','20:00:00.000000','Hatha Yoga'),(33,_binary '',20,'Clase de yoga para todos los niveles','2025-06-17','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 1','10:00:00.000000','09:00:00.000000','Hatha Yoga'),(34,_binary '',15,'Clase de yoga para todos los niveles','2025-06-17','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494245/imagen_3_wtoecu.png','Manuel','Sala 2','18:00:00.000000','17:00:00.000000','Yoga Kundalini'),(35,_binary '',20,'Clase de yoga para todos los niveles','2025-06-17','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 1','19:00:00.000000','18:00:00.000000','Hatha Yoga'),(36,_binary '',20,'Clase de yoga para todos los niveles','2025-06-17','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494250/imagen_12_syhhwj.png','Mariana','Sala 3','21:00:00.000000','20:00:00.000000','Vinyasa Yoga'),(37,_binary '',20,'Clase de yoga para todos los niveles','2025-06-24','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 1','10:00:00.000000','09:00:00.000000','Hatha Yoga'),(38,_binary '',15,'Clase de yoga para todos los niveles','2025-06-24','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494245/imagen_3_wtoecu.png','Manuel','Sala 2','18:00:00.000000','17:00:00.000000','Yoga Kundalini'),(39,_binary '',20,'Clase de yoga para todos los niveles','2025-06-24','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 1','19:00:00.000000','18:00:00.000000','Hatha Yoga'),(40,_binary '',20,'Clase de yoga para todos los niveles','2025-06-24','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494250/imagen_12_syhhwj.png','Mariana','Sala 3','21:00:00.000000','20:00:00.000000','Vinyasa Yoga'),(44,_binary '',20,'Clase de yoga para todos los niveles','2025-06-11','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494252/imagen_15_magxrm.png','Sara','Sala 1','11:00:00.000000','10:00:00.000000','Yoga Suave'),(45,_binary '',15,'Clase de yoga para todos los niveles','2025-06-11','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494245/imagen_2_jlo3al.png','Mariana','Sala 2','19:00:00.000000','18:00:00.000000','Yoga Terapeutico'),(46,_binary '',20,'Clase de yoga para todos los niveles','2025-06-11','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 1','21:00:00.000000','20:00:00.000000','Hatha Yoga'),(47,_binary '',20,'Clase de yoga para todos los niveles','2025-06-18','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494252/imagen_15_magxrm.png','Sara','Sala 1','11:00:00.000000','10:00:00.000000','Yoga Suave'),(48,_binary '',15,'Clase de yoga para todos los niveles','2025-06-18','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494245/imagen_2_jlo3al.png','Mariana','Sala 2','19:00:00.000000','18:00:00.000000','Yoga Terapeutico'),(49,_binary '',20,'Clase de yoga para todos los niveles','2025-06-18','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 1','21:00:00.000000','20:00:00.000000','Hatha Yoga'),(56,_binary '',20,'Clase de yoga para todos los niveles','2025-06-12','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 1','10:00:00.000000','09:00:00.000000','Hatha Yoga'),(57,_binary '',15,'Clase de yoga para todos los niveles','2025-06-12','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494245/imagen_3_wtoecu.png','Manuel','Sala 2','18:00:00.000000','17:00:00.000000','Yoga Kundalini'),(58,_binary '',20,'Clase de yoga para todos los niveles','2025-06-12','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 1','19:00:00.000000','18:00:00.000000','Hatha Yoga'),(59,_binary '',20,'Clase de yoga para todos los niveles','2025-06-12','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494250/imagen_12_syhhwj.png','Mariana','Sala 3','21:00:00.000000','20:00:00.000000','Vinyasa Yoga'),(73,_binary '',20,'Clase de yoga para todos los niveles','2025-06-13','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494248/imagen_8_lu0ptg.png','Manuel','Sala 3','11:00:00.000000','10:00:00.000000','Hatha y Meditacion'),(74,_binary '',20,'Clase de yoga para todos los niveles','2025-06-20','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494248/imagen_8_lu0ptg.png','Manuel','Sala 3','11:00:00.000000','10:00:00.000000','Hatha y Meditacion'),(75,_binary '',20,'Clase de yoga para todos los niveles','2025-06-27','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494248/imagen_8_lu0ptg.png','Manuel','Sala 3','11:00:00.000000','10:00:00.000000','Hatha y Meditacion');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `id` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `description` text,
  `event_date` date DEFAULT NULL,
  `event_time` time(6) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,_binary '','El taller combina una introducción teórica accesible con prácticas experienciales guiadas:\r\nLa perspectiva del yoga sobre la mente y sus fluctuaciones.\r\nPrincipios básicos del mindfulness contemporáneo.\r\nConexiones entre ambas tradiciones.\r\nEl respaldo científico actual.\r\nPrácticas guiadas.\r\nAtención a la respiración: Aprenderás a utilizar el ancla de la respiración como punto de retorno a la presencia.\r\nEscaneo corporal: Una versión adaptada del tradicional yoga \'nidra\' para desarrollar consciencia corporal.\r\nMeditación de la montaña: Práctica para cultivar estabilidad ante las tormentas emocionales.\r\nMovimiento consciente: Llevando la atención plena a la acción.','2025-06-27','18:00:00.000000','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748593876/ai-generated-7895784_1280_qpq1nz.jpg','Studio Yoga','Introducción al Mindfulness.'),(2,_binary '','Explora el amor y la compasión a través del arte.\r\nEl cuarto chakra, conocido como el chakra del corazón o Anahata, está asociado con el amor, la compasión y las relaciones. En este cuarto taller de nuestro ciclo de arteterapia, exploraremos el centro energético que gobierna nuestra capacidad de amar y conectar.\r\nEl puente entre lo físico y lo espiritual.\r\nAnahata ocupa una posición única: es el puente que une los tres chakras inferiores (vinculados a necesidades materiales y emocionales) con los tres superiores (relacionados con la expresión y la conciencia). Cuando abrimos el corazón, transformamos nuestra relación con nosotros mismos y con los demás.','2025-06-27','20:00:00.000000','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746792229/corazones_jsks1x.jpg','Studio Yoga','Amor, compasión y relaciones.'),(3,_binary '','Un viaje multisensorial hacia tu interior.\r\nDesconecta del ruido. Reconecta contigo.\r\nNos complace presentar Sensoralia Music Experience, una experiencia inmersiva que va más allá del simple espectáculo para convertirse en un auténtico viaje interior a través de los sentidos.\r\nUna experiencia mindfulness completa.\r\nSensoralia no es solo un concierto. Es una invitación a dejarte llevar por un flujo de sensaciones que despertarán cada uno de tus sentidos y te conducirán a un estado de presencia plena y bienestar profundo, alineándose con la filosofía de bienestar integral que promovemos.','2025-07-04','20:00:00.000000','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746792825/dance-yoga_dnuwhn.jpg','Cartuja Center','Sensoralia Music Experience'),(4,_binary '','Explora tu creatividad y emociones a través de la Arteterapia Humanista en este taller único. Conectaremos con el chakra sacro (Swadhisthana), centro de nuestra creatividad, placer y adaptabilidad, mediante prácticas de yoga, visualizaciones guiadas y ejercicios artísticos. Desbloquea tu expresión emocional, cultiva relaciones saludables y despierta la alegría de vivir en un espacio de autodescubrimiento y fluidez. ¡Una experiencia transformadora para reconectar contigo mismo!','2025-07-04','20:00:00.000000','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746792229/taller-sexualidad_kzpex4.jpg','Yoga Studio','Sexualidad y alegría de vivir.'),(7,_binary '','Los retiros son algo más que momentos para descansar y aislarse de las grandes ciudades en busca de paz y tranquilidad. Los retiros ofrecen un espacio de encuentro con uno mismo y de recuperación mental. A través del yoga y la meditación, en un entorno natural privilegiado. Información y reservas en recepción y en el mail: studioyogasevilla@gmail.com','2025-10-04','09:00:00.000000','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748449725/taller1_rnmjyj.jpg','Casa rural','Retiro de Yoga');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guide_sections`
--

DROP TABLE IF EXISTS `guide_sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guide_sections` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` text,
  `image_url` varchar(255) DEFAULT NULL,
  `section_order` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guide_sections`
--

LOCK TABLES `guide_sections` WRITE;
/*!40000 ALTER TABLE `guide_sections` DISABLE KEYS */;
INSERT INTO `guide_sections` VALUES (2,'<p><strong>En la lengua clásica de la India, el sánscrito (escrito de otra manera sanskrit), yoga vendría de la palabra</strong> «jug» que quiere decir «unir, conectar, relacionar».</p>\r\n\r\n<p>En lengua indoeuropea, el término más próximo parecer ser «joug». Aunque evoca la obediencia, para los indios, creadores del yoga, el sentido sería más bien el de <strong>la conexión y el unísono.</strong></p>\r\n\r\n<p>Por lo tanto, es el hecho de unir el yo universal (brahman) y el yo individual (atman). De hecho, es el <strong>nexo entre el ser humano con el universo.</strong></p>\r\n\r\n<p>De esta manera, con los movimientos correctos y una respiración adecuada, podremos sentirnos en <strong>armonía tanto psicológica como física y espiritual.</strong></p>\r\n<p>Evidentemente, el yoga no apareció por arte de magia. Es el fruto de muchas pruebas y de una maduración de varios miles de años que han permitido que el yoga se convierta en lo que es en la actualidad.</p>\r\n\r\n<p>Así que, tus clases de yoga no son solamente una sucesión de técnicas para aprender a eliminar el estrés acumulado durante el día.</p>\r\n\r\n<p>Son miles de años los que te contemplan. Tu maestro, el Yoga, crea el lazo entre el pasado, el presente y el futuro.</p>\r\n\r\n<p>«El Yoga es el cese de las fluctuaciones de la mente» Patanjali</p>\r\n\r\n','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748593857/meditation-6816145_1280_y3nz1o.jpg',1,'¿Qué es Yoga?'),(3,' <p>\r\n            El yoga se originó en la India hace más de 5.000 años, emergiendo de tradiciones filosóficas y espirituales profundas. Sus raíces se encuentran en los Vedas y Upanishads, textos antiguos que mencionan conceptos clave como el prana (energía vital) y los chakras (centros energéticos).\r\n        </p>\r\n        <p>\r\n            La transmisión del yoga fue inicialmente oral, de maestro a discípulo, y más tarde se plasmó en textos como los Yoga Sutras de Patanjali, que sistematizaron la práctica.\r\n        </p>\r\n','',2,'Orígenes del Yoga'),(4,'  <p>\r\n            Uno de los textos más importantes es el <strong>Yoga Sutra</strong> de Patanjali, escrito y recopilado entre el siglo II y el V a.C., reúne 195 sutras (frases cortas que han de memorizarse) y 1161 palabras, que define el yoga como \"el cese de las fluctuaciones mentales\" (<em>yogaḥ citta-vṛtti-nirodhaḥ</em>).\r\n        </p>\r\n        <ul>\r\n            <li><strong>Yama</strong>: Código de conducta social (no violencia, verdad, honestidad, control y desapego).</li>\r\n            <li><strong>Niyama</strong>: Conducta individual (limpieza, contento, austeridad, autoestudio, entrega).</li>\r\n            <li><strong>Asana</strong>: Posturas físicas.</li>\r\n            <li><strong>Pranayama</strong>: Control de la respiración y la energía vital.</li>\r\n            <li><strong>Pratyahara</strong>: Interiorización, control de los sentidos.</li>\r\n            <li><strong>Dharana</strong>: Concentración.</li>\r\n            <li><strong>Dhyana</strong>: Meditación.</li>\r\n            <li><strong>Samadhi</strong>: Estado de conciencia plena y unión.</li>\r\n        </ul>\r\n        <p>\r\n            Existen diferentes caminos del yoga: Raja (meditación), Karma (acción), Jnana (conocimiento), Bhakti (devoción) y Hatha (ejercicios físicos).\r\n        </p>','https://youtu.be/mQpBjNtkL3Y?si=o5HaCQEi3rL1ZsqL',3,'Patanjali y los Ocho Pasos del Yoga'),(5,'<p><strong>Descripción:</strong><br>\r\n            Hatha Yoga es uno de los estilos más tradicionales y accesibles. Se caracteriza por un ritmo pausado, ideal para principiantes, con tiempo para la respiración y la alineación en cada postura. Es la base de muchos otros estilos y combina asanas (posturas), pranayama (respiración) y relajación.\r\n        </p>\r\n        <p><strong>Beneficios:</strong></p>\r\n        <ul>\r\n            <li>Mejora la fuerza y la flexibilidad.</li>\r\n            <li>Favorece la relajación y la conciencia corporal.</li>\r\n            <li>Ayuda a reducir el estrés y la ansiedad.</li>\r\n        </ul>\r\n        <p><strong>Posturas típicas:</strong></p>\r\n        <ul>\r\n            <li>Guerrero I, II y III (Virabhadrasana I, II, III)</li>\r\n            <li>Postura del árbol (Vrksasana)</li>\r\n            <li>Postura de la cobra (Bhujangasana)</li>\r\n            <li>Perro boca abajo (Adho Mukha Svanasana)</li>\r\n            <li>Postura de la montaña (Tadasana)</li>\r\n        </ul>\r\n        <p><strong>Recomendaciones:</strong><br>\r\n            Mantén cada postura al menos 30 segundos, con respiraciones profundas. Es ideal para quienes buscan una práctica suave y consciente.\r\n        </p>','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748593878/ai-generated-8993759_1280_dato0n.jpg',4,'Hatha Yoga'),(6,' <p><strong>Descripción:</strong><br>\r\n            Vinyasa Flow es un estilo dinámico en el que las posturas se enlazan en secuencias fluidas sincronizadas con la respiración. Cada movimiento está guiado por una inhalación o exhalación, creando una práctica continua y energética.\r\n        </p>\r\n        <p><strong>Beneficios:</strong></p>\r\n        <ul>\r\n            <li>Mejora la resistencia cardiovascular.</li>\r\n            <li>Desarrolla fuerza y flexibilidad de manera integral.</li>\r\n            <li>Favorece la concentración y la coordinación.</li>\r\n        </ul>\r\n        <p><strong>Características:</strong></p>\r\n        <ul>\r\n            <li>Las clases suelen incluir saludos al sol (Surya Namaskar) y transiciones entre posturas.</li>\r\n            <li>Es ideal para quienes buscan una práctica más activa y desafiante.</li>\r\n        </ul>','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748593950/kike-vega-F2qh3yjz6Jk-unsplash_o4m64i.jpg',5,'Vinyasa Flow'),(8,'        <p><strong>Descripción:</strong><br>\r\n            La meditación mindfulness es una práctica de atención plena que consiste en observar el momento presente sin juicio. Se puede practicar sentados, tumbados o incluso caminando, enfocando la atención en la respiración, sensaciones corporales o pensamientos.\r\n        </p>\r\n        <p><strong>Beneficios:</strong></p>\r\n        <ul>\r\n            <li>Reduce el estrés y la ansiedad.</li>\r\n            <li>Mejora la concentración y la claridad mental.</li>\r\n            <li>Favorece la regulación emocional y el bienestar general.</li>\r\n        </ul>\r\n        <p><strong>Recomendaciones:</strong><br>\r\n            Dedica unos minutos al inicio o final de la práctica de yoga para meditar.<br>\r\n            Utiliza soportes como cojines (zafu) para mantener la postura cómoda y la columna erguida.\r\n        </p>','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748593874/ai-generated-8763808_1280_trkmjw.jpg',6,'Meditacion y Mindfulness');
/*!40000 ALTER TABLE `guide_sections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKf90ivichjaokvmovxpnlm5nin` (`user_id`),
  CONSTRAINT `FK83nsrttkwkb6ym0anu051mtxn` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
INSERT INTO `password_reset_token` VALUES (13,'2025-06-06 15:36:37.076436','feeda2bd-ba12-4134-9950-8ff64f0fe563',33);
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` int NOT NULL,
  `date_reservation` datetime(6) DEFAULT NULL,
  `classes_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb5g9io5h54iwl2inkno50ppln` (`user_id`),
  KEY `FK_reservations_classes` (`classes_id`),
  CONSTRAINT `FK_reservations_classes` FOREIGN KEY (`classes_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `FKb5g9io5h54iwl2inkno50ppln` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (904,'2025-06-01 20:38:52.174756',59,35,_binary ''),(1302,'2025-06-04 23:21:58.239744',48,2,_binary ''),(1303,'2025-06-04 23:22:01.158431',34,2,_binary ''),(1354,'2025-06-05 19:47:18.093808',34,30,_binary ''),(1552,'2025-06-11 10:34:56.385629',45,27,_binary ''),(1554,'2025-06-11 10:35:19.348687',19,27,_binary ''),(1555,'2025-06-11 10:35:39.765313',48,27,_binary ''),(1556,'2025-06-11 10:35:47.698354',34,27,_binary ''),(1557,'2025-06-11 10:36:22.733531',45,21,_binary ''),(1558,'2025-06-11 10:36:25.198363',57,21,_binary ''),(1559,'2025-06-11 10:36:27.913188',19,21,_binary ''),(1560,'2025-06-11 10:36:29.796056',34,21,_binary ''),(1561,'2025-06-11 10:36:32.414337',48,21,_binary ''),(1562,'2025-06-11 10:37:31.351897',45,22,_binary ''),(1563,'2025-06-11 10:37:33.713274',57,22,_binary ''),(1564,'2025-06-11 10:37:36.644047',19,22,_binary ''),(1565,'2025-06-11 10:37:38.447612',34,22,_binary ''),(1566,'2025-06-11 10:37:40.311890',48,22,_binary ''),(1567,'2025-06-11 10:38:05.593754',45,23,_binary ''),(1568,'2025-06-11 10:38:07.099190',57,23,_binary ''),(1569,'2025-06-11 10:38:09.113338',19,23,_binary ''),(1570,'2025-06-11 10:38:16.283476',34,23,_binary ''),(1571,'2025-06-11 10:38:18.078142',48,23,_binary ''),(1572,'2025-06-11 10:38:39.636357',45,24,_binary ''),(1573,'2025-06-11 10:38:41.513809',57,24,_binary ''),(1574,'2025-06-11 10:38:43.796305',19,24,_binary ''),(1575,'2025-06-11 10:38:45.496353',34,24,_binary ''),(1576,'2025-06-11 10:38:56.825581',48,24,_binary ''),(1577,'2025-06-11 10:39:09.966035',47,24,_binary ''),(1578,'2025-06-11 10:39:31.983808',45,26,_binary ''),(1579,'2025-06-11 10:39:36.558459',57,26,_binary ''),(1580,'2025-06-11 10:39:38.850494',44,26,_binary ''),(1581,'2025-06-11 10:39:43.399600',19,26,_binary ''),(1582,'2025-06-11 10:39:45.152359',34,26,_binary ''),(1583,'2025-06-11 10:39:51.261909',47,26,_binary ''),(1584,'2025-06-11 10:40:20.270269',47,27,_binary ''),(1585,'2025-06-11 10:40:40.696361',45,28,_binary ''),(1586,'2025-06-11 10:40:42.296793',57,28,_binary ''),(1587,'2025-06-11 10:40:44.654699',19,28,_binary ''),(1588,'2025-06-11 10:40:46.295416',34,28,_binary ''),(1589,'2025-06-11 10:40:48.227741',48,28,_binary ''),(1591,'2025-06-11 10:41:36.613801',45,31,_binary ''),(1592,'2025-06-11 10:41:37.863126',57,31,_binary ''),(1593,'2025-06-11 10:41:39.917237',19,31,_binary ''),(1594,'2025-06-11 10:41:41.718368',34,31,_binary ''),(1595,'2025-06-11 10:41:53.661870',47,31,_binary ''),(1596,'2025-06-11 10:44:26.042615',45,42,_binary ''),(1597,'2025-06-11 10:44:33.314903',34,42,_binary ''),(1598,'2025-06-11 10:44:39.183963',57,42,_binary ''),(1599,'2025-06-11 10:44:48.446931',48,42,_binary ''),(1600,'2025-06-11 10:46:02.215297',34,33,_binary ''),(1601,'2025-06-11 10:46:46.465642',34,34,_binary ''),(1602,'2025-06-11 10:47:11.914020',34,36,_binary ''),(1603,'2025-06-11 10:47:33.797114',34,37,_binary ''),(1652,'2025-06-11 16:42:37.249985',45,2,_binary ''),(1653,'2025-06-11 16:44:11.348805',46,27,_binary ''),(1654,'2025-06-11 16:44:16.584804',73,27,_binary ''),(1655,'2025-06-11 16:44:22.038367',49,27,_binary ''),(1657,'2025-06-11 16:44:35.179026',19,2,_binary ''),(1658,'2025-06-11 16:44:45.266577',58,27,_binary ''),(1702,'2025-06-14 20:38:02.384606',44,2,_binary ''),(1752,'2025-06-15 13:53:44.294533',46,2,_binary '');
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations_seq`
--

DROP TABLE IF EXISTS `reservations_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations_seq`
--

LOCK TABLES `reservations_seq` WRITE;
/*!40000 ALTER TABLE `reservations_seq` DISABLE KEYS */;
INSERT INTO `reservations_seq` VALUES (1851);
/*!40000 ALTER TABLE `reservations_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(3,'INSTRUCTOR'),(2,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `asunto` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi7nux8teh1hv8gafl66ebmum0` (`usuario_id`),
  CONSTRAINT `FKi7nux8teh1hv8gafl66ebmum0` FOREIGN KEY (`usuario_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (11,'No puedo acceder a mi cuenta','Intento iniciar sesión y me dice que la contraseña es incorrecta.','Abierto','2025-05-27 20:39:30.000000',21),(12,'Error en el pago','Al intentar pagar la suscripción, la página se queda cargando.','En progreso','2025-05-27 20:39:30.000000',22),(13,'Sugerencia de mejora','Sería útil tener una sección de preguntas frecuentes.','Cerrado','2025-05-27 20:39:30.000000',23),(14,'Problema con la app móvil','La aplicación se cierra sola al intentar ver las clases grabadas.','Abierto','2025-05-27 20:39:30.000000',24),(16,'Error al actualizar perfil','Intento cambiar mi foto de perfil y no se guarda.','Abierto','2025-05-27 20:39:30.000000',26),(17,'Clase cancelada sin aviso','Hoy se canceló una clase y no recibí ninguna notificación.','Cerrado','2025-05-27 20:39:30.000000',27),(18,'Problema con el calendario','Las fechas de las clases aparecen desordenadas en el calendario.','Abierto','2025-05-27 20:39:30.000000',28),(19,'No puedo cancelar mi suscripción','Intento cancelar mi suscripción y me da error.','En progreso','2025-05-27 20:39:30.000000',21),(20,'Duda sobre tarifas','¿Existen descuentos para estudiantes?','Cerrado','2025-05-27 20:39:30.000000',22),(21,'No puedo ver clases en diferido','El video no carga o da error.','Abierto','2025-05-27 20:39:30.000000',23),(22,'Error al reservar clase','Me aparece un mensaje de error al reservar.','En progreso','2025-05-27 20:39:30.000000',24),(24,'Problema con notificaciones push','No recibo avisos en el móvil.','Cerrado','2025-05-27 20:39:30.000000',26),(25,'Consulta sobre horarios','¿Habrá clases en agosto?','Abierto','2025-05-27 20:39:30.000000',27),(26,'Problema con el pago con tarjeta','Mi tarjeta es válida pero la rechaza.','En progreso','2025-05-27 20:39:30.000000',28),(27,'No puedo descargar factura','Al pulsar descargar factura no hace nada.','Abierto','2025-05-27 20:39:30.000000',21),(28,'Error al cambiar contraseña','El sistema no me deja guardar la nueva contraseña.','Cerrado','2025-05-27 20:39:30.000000',22),(29,'Sugerencia: más clases de pilates','Me gustaría ver más variedad de clases de pilates.','Abierto','2025-05-27 20:39:30.000000',23),(30,'Problema con el chat de soporte','No puedo enviar mensajes al soporte.','En progreso','2025-05-27 20:39:30.000000',24);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `first_last_name` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `second_last_name` varchar(50) DEFAULT NULL,
  `verification_code` varchar(255) DEFAULT NULL,
  `rol_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FKgqf6aajl2lwd68xv9ypxpo7ra` (`rol_id`),
  CONSTRAINT `FKgqf6aajl2lwd68xv9ypxpo7ra` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@example.com',_binary '','Principal','Admin','','$2a$10$J64YiyHUxUN.j0yO5EwvSel.MSeTlcQUczxqEGi1bQzRF1Sn8A312','','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-05-08','','',1),(2,'usuario@example.com',_binary '','Normal','Usuario','','$2a$10$3gQofPB3SGOoZqh8RJXnlOEoAQE872ZVA1q1DWiqVbdyq911S8YlW','','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-05-08','','',2),(21,'ana@example.com',_binary '','López','Ana','','$2a$10$Jj.mFKsO.TyoQ82RKVDbNONRdp2GSWi5Fc4IvZUIg3VoblW5R3ElW','600123456','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912440/mujer2_gq3pw7.jpg','2025-05-08','Pérez','Pérez',2),(22,'carlos@example.com',_binary '','García','Carlos','','$2a$10$2.hXmk85.C9EMjsK4DZAqeX7iqtm5vd0TkVRJn3W81ermZjQn8Yfa','600654321','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912437/hombre4_nmnzgl.jpg','2025-05-08',NULL,'',2),(23,'maria@example.com',_binary '','Fernández','María','','$2a$10$NPUfvsTXaqSSg/t0/ADeMuGPY04rxqRPVuIJwTAMcY6jlGIt4dbha','600987654','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912441/mujer3_gl86fd.jpg','2025-05-08','Gómez','Gómez',2),(24,'luis@example.com',_binary '','Martínez','Luis','','$2a$10$7zoNWkR5K3YLWpoI5UIk6eX8bKooinLNH1hovTxhQPJI.R1vZ49N.','600456789','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912436/hombre2_xawn7u.jpg','2025-05-08','Sánchez','Sánchez',2),(26,'javier@example.com',_binary '','Pérez','Javier','','$2a$10$7/W2XDT1rs2PQ71pSXK/JeMe/G1NgCuzBuArDsRKO.7JIhy97Mvfy','600789123','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912437/hombre3_lwnanf.jpg','2025-05-10','López','López',2),(27,'antonio@example.com',_binary '','Garcia','Antonio','','$2a$10$zDUGPU/qOXFaW6gLDeA.euTr4NBB3NM7i9ql8dVZWcPalJoRt7ky.','666555444','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912436/hombre1_f01jj0.jpg','2025-05-10','Garcia','Garcia',2),(28,'gema@example.com',_binary '','Rodriguez','Gema','','$2a$10$x1Vw7I2memIAmB3Ra/xp9.dhJvHb8EmTLBUlI7Vmm5eXJxj0S.LSO','666555333','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912442/mujer5_jazirk.jpg','2025-05-11','Rodriguez','Rodriguez',2),(30,'suj884@gmail.com',_binary '','Uber','Susana','','$2a$10$uuasme.GRUODRyGGfg0WguEzNebvCJRXpsi2DLbIZ/bSblUcW/ctK','686572928','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-05-28','Jimenez','',1),(31,'elena@example.com',_binary '','Ruiz','Elena','','$2a$10$HaMSs9UGZo/pO7M1NjLmb.iogeeadbaoYJAYYjvM3Fzc6qjHu4mXi','600321654','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912437/mujer1_sv6axr.jpg','2025-05-08','Hernández','Hernández',2),(32,'joseantoniouber138@gmail.com',_binary '','Uber','Jose Antonio','','$2a$10$dCe9.fquGFtNWCIh.y0NgeODgl8AQ4YcCZ21Q8oUp30MmSZzoNGye','','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-05-30','Rivas','',2),(33,'sanjose2@hotmail.com',_binary '','Uber','Jose Antonio','','$2a$10$a7RAnlTNP/NXfrPLJT3E4.damT4Q55nRfJHuEgFGiPPrUYzwwF7mC','','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-05-30','Jimenez','',2),(34,'virginiiamzz@gmail.com',_binary '','Muñoz','Virginia','','$2a$10$PP8FUp73ZW6SKdH/k04aJuNuLBq0jNO4Oi.uY5wT6ys3SDzgxATHK','666555444','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-05-31','Castro','',2),(35,'angelaborgescantarino@gmail.com',_binary '','Borges','Angela','','$2a$10$vOqsSbUULsBcbFxTPufb0uTPXJivEOSf/ycP9pL2fE05DvG8P1OC6','666555444','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-06-01','Cantarino','',2),(36,'romangarpablo@gmail.com',_binary '','Román','Pablo','','$2a$10$HEIdxNFVgmamjcJ04uEPR.Pht0RPIDexs05QW.YQHpINWmTq2PlXy','666555332','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-06-01','','',2),(37,'javivelez42@gmail.com',_binary '','Velez','Javier','','$2a$10$VcYOs.x3A53rC5.A.ivIR.m5GQ44AoXBHxqI.O0OUwDFcKVuC/Rte','666555444','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-06-02',' ','',2),(38,'iphone16nuevodeangel@gmail.com',_binary '','Cuevas','Angel ','','$2a$10$d3YvOjtYjStJMKGg0af9xe6HzWDcHl3l/OO2bwhJqS6R5wiXIQk7W','666999888','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-06-02','','',2),(39,'josemugarcia2002@gmail.com',_binary '','Muñoz','Jose Antonio','','$2a$10$I1BZlAf2zWNGDNBfjddXteAmD2l749QImJQJH0.G6V2x7y0a/Qbxq','666555444','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-06-04','Garcia','',2),(40,'jrortega@minsait.com',_binary '','Romero','Jose Antonio','','$2a$10$BR250In8MYglY97GCPlpleSaNYsphrIZQSL26vuGAYp0nCOWXiujW','','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-06-04','Ortega','',2),(41,'suber@minsait.com',_binary '','Uber','Susana','','$2a$10$4tdVYiKWv4f4vSDzzfs.qOxgGkEqdEPKRmRq1MpRNyRCCxCDEL.Gi','686572928','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-06-04','Jimenez','',2),(42,'suj884@hotmail.com',_binary '','Uber','Susana','','$2a$10$J.vzYChHQ4ptZ3MlrJbvJuNqITCp1tYv2YNJ4VgCpCZyuqTv5vkrO','686572928','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-06-11','Jimenez','',2),(43,'fortiz@iesruizgijon.com',_binary '','Ortiz','Fernando','','$2a$10$GC7duFwHeXs7LMDcQxlhLuOysKBOaR6Lh3BTV89ZGMrVHcSavgYdG','','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-06-14','Sierra','',2),(44,'jsoldado@iesruizgijon.com',_binary '','Soldado','Javier','','$2a$10$nJgFqi6j5A5AoE2mx3AS2.Qq4RpYhQWKaYqlrkFKLHAbDmRctXhMC','','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-06-14','','',2),(45,'fjmatias@iesruizgijon.com',_binary '','Matias','Francisco','','$2a$10$O3KplINWgSdbvsAyfb6AGewMN4B49xJcEcMLgb557sgMBiwxwLjCW','','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748580342/no-image-icon-23479_q7mujt.png','2025-06-14','','',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-18 21:19:32

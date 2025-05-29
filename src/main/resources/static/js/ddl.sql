
USE railway;
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
INSERT INTO `blog_posts` VALUES (1,'<p>En el espejo de la mente agitada, vemos solo reflejos distorsionados. Esta es la primera verdad. La segunda es que, al calmar la mente, la realidad se muestra clara y serena. En este artículo exploramos técnicas y consejos para aquietar los pensamientos y encontrar paz interior a través de la práctica regular de yoga y meditación.</p>','https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=800&q=80','2025-05-01','En el espejo de la mente agitada, vemos solo reflejos distorsionados. Esta es la primera verdad. La segunda es que …','En el espejo de la mente agitada'),(2,'<p>Nuestras clases de Yoga Salud, dirigidas por la Dra. Irene Escames, traen a nuestro estudio una combinación excepcional: la precisión médica y la sabiduría ancestral del yoga. Descubre cómo esta disciplina puede ayudarte a mejorar tu bienestar físico y mental, con ejercicios adaptados a todas las edades y condiciones.</p>','https://images.unsplash.com/photo-1517841905240-472988babdf9?auto=format&fit=crop&w=800&q=80','2025-04-20','Nuestras clases de Yoga Salud, dirigidas por la Dra. Irene Escames, traen a nuestro estudio una combinación excepcional: la precisión …','Yoga Salud con la Dra. Irene Escames'),(3,'<p>Hay una sabiduría en las manos que han sembrado décadas. Una memoria en las rodillas que conocen el peso de los años y la experiencia. Este post rinde homenaje a nuestros practicantes veteranos y explora cómo el yoga puede acompañarnos y enriquecernos a cualquier edad.</p>','https://images.unsplash.com/photo-1464983953574-0892a716854b?auto=format&fit=crop&w=800&q=80','2025-04-10','Hay una sabiduría en las manos que han sembrado décadas. Una memoria en las rodillas que conocen el peso de …','Sabiduría en las manos, memoria en las rodillas'),(4,'<p>La Arteterapia Humanista es mucho más que dibujar, pintar o moldear; es una forma de conectar contigo mismo a través de la creatividad. En este artículo te contamos cómo el arte puede ser un camino de autoconocimiento, sanación y expresión emocional, complementando la práctica de yoga y meditación.</p>','https://images.unsplash.com/photo-1503676382389-4809596d5290?auto=format&fit=crop&w=800&q=80','2025-03-30','La Arteterapia Humanista es mucho más que dibujar, pintar o moldear; es una forma de conectar contigo mismo a través …','Arteterapia Humanista: mucho más que dibujar'),(5,'<p>Nuestra visión del yoga, arte, creatividad, salud y bienestar es integradora y abierta. Creemos en una práctica que va más allá de lo físico, abarcando la mente, el espíritu y la expresión creativa. Te invitamos a descubrir nuestro enfoque y a sumarte a nuestra comunidad.</p>','https://images.unsplash.com/photo-1465101046530-73398c7f28ca?auto=format&fit=crop&w=800&q=80','2025-03-20','Nuestra visión del yoga, arte, creatividad, salud y bienestar.','Nuestra visión: yoga, arte, creatividad y bienestar');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,_binary '',20,'Clase de yoga para principiantes','2025-06-02','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494252/imagen_15_magxrm.png','Sara','Sala 1','11:00:00.000000','10:00:00.000000','Yoga Suave'),(2,_binary '',15,'Prevención y recuperación de lesiones.','2025-06-02','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494245/imagen_2_jlo3al.png','Mariana','Sala 2','19:00:00.000000','18:00:00.000000','Yoga Terapéutico'),(4,_binary '',20,'Clase suave para respiración y relajación.','2025-06-02','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 1','21:00:00.000000','20:00:00.000000','Hatha Yoga'),(12,_binary '',20,'Clase suave para respiración y relajación.','2025-06-03','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494246/imagen_6_nu1omz.png','Sara','Sala 1','10:00:00.000000','09:00:00.000000','Hatha Yoga'),(13,_binary '',15,'Sesión energética con respiración y meditación.','2025-06-03','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494245/imagen_3_wtoecu.png','Manuel','Sala 3','18:00:00.000000','17:00:00.000000','Yoga Kundalini'),(14,_binary '',15,'Sesión energética con respiración y meditación.','2025-06-05','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748494245/imagen_3_wtoecu.png','Manuel','Sala 3','18:00:00.000000','17:00:00.000000','Yoga Kundalini');
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
INSERT INTO `events` VALUES (1,_binary '','El taller combina una introducción teórica accesible con prácticas experienciales guiadas:\r\nLa perspectiva del yoga sobre la mente y sus fluctuaciones.\r\nPrincipios básicos del mindfulness contemporáneo.\r\nConexiones entre ambas tradiciones.\r\nEl respaldo científico actual.\r\nPrácticas guiadas.\r\nAtención a la respiración: Aprenderás a utilizar el ancla de la respiración como punto de retorno a la presencia.\r\nEscaneo corporal: Una versión adaptada del tradicional yoga \'nidra\' para desarrollar consciencia corporal.\r\nMeditación de la montaña: Práctica para cultivar estabilidad ante las tormentas emocionales.\r\nMovimiento consciente: Llevando la atención plena a la acción.','2025-06-27','18:00:00.000000','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746792296/taller-mindfulness_x5cnxu.jpg','Studio Yoga','Taller de Introducción al Mindfulness.'),(2,_binary '','Explora el amor y la compasión a través del arte.\r\nEl cuarto chakra, conocido como el chakra del corazón o Anahata, está asociado con el amor, la compasión y las relaciones. En este cuarto taller de nuestro ciclo de arteterapia, exploraremos el centro energético que gobierna nuestra capacidad de amar y conectar.\r\nEl puente entre lo físico y lo espiritual.\r\nAnahata ocupa una posición única: es el puente que une los tres chakras inferiores (vinculados a necesidades materiales y emocionales) con los tres superiores (relacionados con la expresión y la conciencia). Cuando abrimos el corazón, transformamos nuestra relación con nosotros mismos y con los demás.','2025-06-27','20:00:00.000000','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746792229/corazones_jsks1x.jpg','Studio Yoga','Amor, compasión y relaciones.'),(3,_binary '','Un viaje multisensorial hacia tu interior.\r\nDesconecta del ruido. Reconecta contigo.\r\nNos complace presentar Sensoralia Music Experience, una experiencia inmersiva que va más allá del simple espectáculo para convertirse en un auténtico viaje interior a través de los sentidos.\r\nUna experiencia mindfulness completa.\r\nSensoralia no es solo un concierto. Es una invitación a dejarte llevar por un flujo de sensaciones que despertarán cada uno de tus sentidos y te conducirán a un estado de presencia plena y bienestar profundo, alineándose con la filosofía de bienestar integral que promovemos.','2025-07-04','20:00:00.000000','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746792825/dance-yoga_dnuwhn.jpg','Cartuja Center','Sensoralia Music Experience'),(4,_binary '','Explora tu creatividad y emociones a través de la Arteterapia Humanista en este taller único. Conectaremos con el chakra sacro (Swadhisthana), centro de nuestra creatividad, placer y adaptabilidad, mediante prácticas de yoga, visualizaciones guiadas y ejercicios artísticos. Desbloquea tu expresión emocional, cultiva relaciones saludables y despierta la alegría de vivir en un espacio de autodescubrimiento y fluidez. ¡Una experiencia transformadora para reconectar contigo mismo!','2025-07-04','20:00:00.000000','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746792229/taller-sexualidad_kzpex4.jpg','Yoga Studio','Sexualidad y alegría de vivir.'),(7,_binary '','Los retiros son algo más que momentos para descansar y aislarse de las grandes ciudades en busca de paz y tranquilidad. Los retiros ofrecen un espacio de encuentro con uno mismo y de recuperación mental. A través del yoga y la meditación, en un entorno natural privilegiado. Información y reservas en recepción y en el mail: studioyogasevilla@gmail.com','2025-10-04','09:00:00.000000','https://res.cloudinary.com/dr5e7n5yp/image/upload/v1748449725/taller1_rnmjyj.jpg','Casa rural','Retiro de Yoga');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guide_sections`
--

LOCK TABLES `guide_sections` WRITE;
/*!40000 ALTER TABLE `guide_sections` DISABLE KEYS */;
INSERT INTO `guide_sections` VALUES (2,'<p><strong>En la lengua clásica de la India, el sánscrito (escrito de otra manera sanskrit), yoga vendría de la palabra</strong> «jug» que quiere decir «unir, conectar, relacionar».</p>\r\n\r\n<p>En lengua indoeuropea, el término más próximo parecer ser «joug». Aunque evoca la obediencia, para los indios, creadores del yoga, el sentido sería más bien el de <strong>la conexión y el unísono.</strong></p>\r\n\r\n<p>Por lo tanto, es el hecho de unir el yo universal (brahman) y el yo individual (atman). De hecho, es el <strong>nexo entre el ser humano con el universo.</strong></p>\r\n\r\n<p>De esta manera, con los movimientos correctos y una respiración adecuada, podremos sentirnos en <strong>armonía tanto psicológica como física y espiritual.</strong></p>\r\n<p>Evidentemente, el yoga no apareció por arte de magia. Es el fruto de muchas pruebas y de una maduración de varios miles de años que han permitido que el yoga se convierta en lo que es en la actualidad.</p>\r\n\r\n<p>Así que, tus clases de yoga no son solamente una sucesión de técnicas para aprender a eliminar el estrés acumulado durante el día.</p>\r\n\r\n<p>Son miles de años los que te contemplan. Tu maestro, el Yoga, crea el lazo entre el pasado, el presente y el futuro.</p>\r\n\r\n<p>«El Yoga es el cese de las fluctuaciones de la mente» Patanjali</p>\r\n\r\n','https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=400&q=80',1,'¿Qué es Yoga?'),(3,' <p>\r\n            El yoga se originó en la India hace más de 5.000 años, emergiendo de tradiciones filosóficas y espirituales profundas. Sus raíces se encuentran en los Vedas y Upanishads, textos antiguos que mencionan conceptos clave como el prana (energía vital) y los chakras (centros energéticos).\r\n        </p>\r\n        <p>\r\n            La transmisión del yoga fue inicialmente oral, de maestro a discípulo, y más tarde se plasmó en textos como los Yoga Sutras de Patanjali, que sistematizaron la práctica.\r\n        </p>\r\n','',2,'Orígenes del Yoga'),(4,'  <p>\r\n            Uno de los textos más importantes es el <strong>Yoga Sutra</strong> de Patanjali, escrito y recopilado entre el siglo II y el V a.C., reúne 195 sutras (frases cortas que han de memorizarse) y 1161 palabras, que define el yoga como \"el cese de las fluctuaciones mentales\" (<em>yogaḥ citta-vṛtti-nirodhaḥ</em>).\r\n        </p>\r\n        <ul>\r\n            <li><strong>Yama</strong>: Código de conducta social (no violencia, verdad, honestidad, control y desapego).</li>\r\n            <li><strong>Niyama</strong>: Conducta individual (limpieza, contento, austeridad, autoestudio, entrega).</li>\r\n            <li><strong>Asana</strong>: Posturas físicas.</li>\r\n            <li><strong>Pranayama</strong>: Control de la respiración y la energía vital.</li>\r\n            <li><strong>Pratyahara</strong>: Interiorización, control de los sentidos.</li>\r\n            <li><strong>Dharana</strong>: Concentración.</li>\r\n            <li><strong>Dhyana</strong>: Meditación.</li>\r\n            <li><strong>Samadhi</strong>: Estado de conciencia plena y unión.</li>\r\n        </ul>\r\n        <p>\r\n            Existen diferentes caminos del yoga: Raja (meditación), Karma (acción), Jnana (conocimiento), Bhakti (devoción) y Hatha (ejercicios físicos).\r\n        </p>','https://youtu.be/mQpBjNtkL3Y?si=o5HaCQEi3rL1ZsqL',3,'Patanjali y los Ocho Pasos del Yoga'),(5,'<ul class=\"asana-list\">\r\n                    <li><strong>Saludo al Sol (Surya Namaskar):</strong> Secuencia dinámica de estiramientos y respiración.</li>\r\n                    <li><strong>Perro Boca Abajo (Adho Mukha Svanasana):</strong> Estira y fortalece la espalda y piernas.</li>\r\n                    <li><strong>Árbol (Vrksasana):</strong> Mejora el equilibrio y la concentración.</li>\r\n                    <li><strong>Montaña (Tadasana):</strong> Base para posturas de pie y alineamiento corporal.</li>\r\n                    <li><strong>Silla (Utkatasana):</strong> Fortalece piernas y espalda baja.</li>\r\n                    <li><strong>Guerrero I y II (Virabhadrasana I y II):</strong> Fuerza y estabilidad.</li>\r\n                    <li><strong>Triángulo (Utthita Trikonasana):</strong> Estira muslos y caderas.</li>\r\n                    <li><strong>Ángulo Lateral Extendido (Utthita Parsvakonasana):</strong> Estira los costados y fortalece piernas.</li>\r\n                    <li><strong>Perro Boca Arriba (Urdhva Mukha Svanasana) y Cobra (Bhujangasana):</strong> Abren el pecho y fortalecen la espalda.</li>\r\n                    <li><strong>Camello (Ustrasana):</strong> Retroflexión que estira la parte frontal del cuerpo.</li>\r\n                    <li><strong>Águila (Garudasana):</strong> Mejora el equilibrio y la coordinación.</li>\r\n                    <li><strong>Pinza (Paschimottanasana):</strong> Estira la espalda y promueve la relajación.</li>\r\n                    <li><strong>Plancha (Chaturanga Dandasana):</strong> Fortalece brazos y abdomen.</li>\r\n                    <li><strong>Cuervo (Bakasana):</strong> Equilibrio avanzado en brazos.</li>\r\n                    <li><strong>Sirsasana (Sobre la cabeza):</strong> Mejora la circulación y el enfoque mental.</li>\r\n                    <li><strong>Bailarín (Natarajasana):</strong> Equilibrio, fuerza y concentración.</li>\r\n                    <li><strong>Espagat (Hanumanasana):</strong> Flexibilidad en piernas e ingles.</li>\r\n                    <li><strong>Posturas de meditación (Padmasana, Sukhasana, Virasana):</strong> Base para la meditación.</li>\r\n                    <li><strong>Vela y Arado (Salamba Sarvangasana y Halasana):</strong> Inversiones que relajan y estimulan el cuerpo.</li>\r\n                    <li><strong>Pez (Matsyasana):</strong> Estira el pecho y el cuello.</li>\r\n                    <li><strong>Ocho Ángulos (Astavakrasana):</strong> Desafía la fuerza y coordinación.</li>\r\n                </ul>','https://images.unsplash.com/photo-1518717758536-85ae29035b6d?auto=format&fit=crop&w=400&q=80',4,'Tipos de Asanas (Posturas de Yoga)'),(6,' <ul class=\"benefit-list\">\r\n            <li><strong>Mejora de la flexibilidad y fuerza:</strong> Las asanas estiran y fortalecen todo el cuerpo.</li>\r\n            <li><strong>Reducción del estrés y mejora de la salud mental:</strong> Pranayama y meditación calman la mente y reducen la ansiedad.</li>\r\n            <li><strong>Salud cardiovascular:</strong> Ayuda a reducir la presión arterial y mejorar la circulación.</li>\r\n            <li><strong>Alivio del dolor crónico:</strong> Mejora la postura y reduce tensiones.</li>\r\n            <li><strong>Mejora de la respiración:</strong> Pranayama aumenta la capacidad pulmonar y la energía vital.</li>\r\n            <li><strong>Conciencia corporal:</strong> Favorece el equilibrio y la coordinación.</li>\r\n            <li><strong>Gestión del peso:</strong> Algunas variantes ayudan a quemar calorías y regular el apetito.</li>\r\n            <li><strong>Estilo de vida saludable:</strong> Fomenta la autoconciencia y mejores hábitos.</li>\r\n        </ul>','https://images.unsplash.com/photo-1465101046530-73398c7f28ca?auto=format&fit=crop&w=400&q=80',5,'Beneficios de la Práctica del Yoga');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
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
INSERT INTO `reservations` VALUES (52,'2025-05-26 18:50:19.895091',14,2,_binary ''),(53,'2025-05-26 18:50:34.235986',12,2,_binary ''),(102,'2025-05-26 18:55:29.000904',2,2,_binary ''),(152,'2025-05-26 19:37:05.695762',14,27,_binary ''),(153,'2025-05-26 19:37:08.242135',12,27,_binary ''),(154,'2025-05-26 19:37:09.649527',2,27,_binary ''),(203,'2025-05-28 11:51:30.181931',12,30,_binary ''),(204,'2025-05-28 11:51:31.773005',14,30,_binary ''),(205,'2025-05-28 11:51:51.042002',13,30,_binary ''),(206,'2025-05-28 11:51:55.265132',4,30,_binary ''),(302,'2025-05-29 10:26:17.892660',1,2,_binary '');
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
INSERT INTO `reservations_seq` VALUES (401);
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
INSERT INTO `tickets` VALUES (11,'No puedo acceder a mi cuenta','Intento iniciar sesión y me dice que la contraseña es incorrecta.','En progreso','2025-05-27 20:39:30.000000',21),(12,'Error en el pago','Al intentar pagar la suscripción, la página se queda cargando.','En progreso','2025-05-27 20:39:30.000000',22),(13,'Sugerencia de mejora','Sería útil tener una sección de preguntas frecuentes.','Cerrado','2025-05-27 20:39:30.000000',23),(14,'Problema con la app móvil','La aplicación se cierra sola al intentar ver las clases grabadas.','Abierto','2025-05-27 20:39:30.000000',24),(16,'Error al actualizar perfil','Intento cambiar mi foto de perfil y no se guarda.','Abierto','2025-05-27 20:39:30.000000',26),(17,'Clase cancelada sin aviso','Hoy se canceló una clase y no recibí ninguna notificación.','Cerrado','2025-05-27 20:39:30.000000',27),(18,'Problema con el calendario','Las fechas de las clases aparecen desordenadas en el calendario.','Abierto','2025-05-27 20:39:30.000000',28),(19,'No puedo cancelar mi suscripción','Intento cancelar mi suscripción y me da error.','En progreso','2025-05-27 20:39:30.000000',21),(20,'Duda sobre tarifas','¿Existen descuentos para estudiantes?','Cerrado','2025-05-27 20:39:30.000000',22),(21,'No puedo ver clases en diferido','El video no carga o da error.','Abierto','2025-05-27 20:39:30.000000',23),(22,'Error al reservar clase','Me aparece un mensaje de error al reservar.','En progreso','2025-05-27 20:39:30.000000',24),(24,'Problema con notificaciones push','No recibo avisos en el móvil.','Cerrado','2025-05-27 20:39:30.000000',26),(25,'Consulta sobre horarios','¿Habrá clases en agosto?','Abierto','2025-05-27 20:39:30.000000',27),(26,'Problema con el pago con tarjeta','Mi tarjeta es válida pero la rechaza.','En progreso','2025-05-27 20:39:30.000000',28),(27,'No puedo descargar factura','Al pulsar descargar factura no hace nada.','Abierto','2025-05-27 20:39:30.000000',21),(28,'Error al cambiar contraseña','El sistema no me deja guardar la nueva contraseña.','Cerrado','2025-05-27 20:39:30.000000',22),(29,'Sugerencia: más clases de pilates','Me gustaría ver más variedad de clases de pilates.','Abierto','2025-05-27 20:39:30.000000',23),(30,'Problema con el chat de soporte','No puedo enviar mensajes al soporte.','En progreso','2025-05-27 20:39:30.000000',24);
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
  `password` varchar(255) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@example.com',_binary '','Principal','Admin',NULL,'$2a$10$J64YiyHUxUN.j0yO5EwvSel.MSeTlcQUczxqEGi1bQzRF1Sn8A312',NULL,NULL,'2025-05-08',NULL,NULL,1),(2,'usuario@example.com',_binary '','Normal','Usuario',NULL,'$2a$10$3gQofPB3SGOoZqh8RJXnlOEoAQE872ZVA1q1DWiqVbdyq911S8YlW',NULL,NULL,'2025-05-08',NULL,NULL,2),(21,'ana@example.com',_binary '','López','Ana','','$2a$10$Jj.mFKsO.TyoQ82RKVDbNONRdp2GSWi5Fc4IvZUIg3VoblW5R3ElW','600123456','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912440/mujer2_gq3pw7.jpg','2025-05-08','Pérez','Pérez',2),(22,'carlos@example.com',_binary '','García','Carlos','','$2a$10$2.hXmk85.C9EMjsK4DZAqeX7iqtm5vd0TkVRJn3W81ermZjQn8Yfa','600654321','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912437/hombre4_nmnzgl.jpg','2025-05-08',NULL,'',2),(23,'maria@example.com',_binary '','Fernández','María','','$2a$10$NPUfvsTXaqSSg/t0/ADeMuGPY04rxqRPVuIJwTAMcY6jlGIt4dbha','600987654','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912441/mujer3_gl86fd.jpg','2025-05-08','Gómez','Gómez',2),(24,'luis@example.com',_binary '','Martínez','Luis','','$2a$10$7zoNWkR5K3YLWpoI5UIk6eX8bKooinLNH1hovTxhQPJI.R1vZ49N.','600456789','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912436/hombre2_xawn7u.jpg','2025-05-08','Sánchez','Sánchez',2),(26,'javier@example.com',_binary '','Pérez','Javier','','$2a$10$MPAFySsUWpMheDqPZHWL2uOZrewVNE/TvFh/hGBSKtlRP1jQ6rEYG','600789123','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912437/hombre3_lwnanf.jpg','2025-05-10','López','López',2),(27,'antonio@example.com',_binary '','Garcia','Antonio','','$2a$10$zDUGPU/qOXFaW6gLDeA.euTr4NBB3NM7i9ql8dVZWcPalJoRt7ky.','666555444','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912436/hombre1_f01jj0.jpg','2025-05-10','Garcia','Garcia',2),(28,'gema@example.com',_binary '','Rodriguez','Gema','','$2a$10$s5f4S.U9lmx6E68eI8n9AuImli05WwLg/55P6rPq74CQt0ToK4SKW','666555333','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912442/mujer5_jazirk.jpg','2025-05-11','Rodriguez','Rodriguez',2),(30,'suj884@gmail.com',_binary '','Uber','Susana','','$2a$10$ex/0eleQv5DFAbIzccVU8uatkRXwrluC4AHjKx7p/D8LLAjPYNYYW','686572928','','2025-05-28','Jimenez','',2),(31,'elena@example.com',_binary '','Ruiz','Elena','','$2a$10$HaMSs9UGZo/pO7M1NjLmb.iogeeadbaoYJAYYjvM3Fzc6qjHu4mXi','600321654','https://res.cloudinary.com/dpimnxbbk/image/upload/v1746912437/mujer1_sv6axr.jpg','2025-05-08','Hernández','Hernández',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'yogadb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-29 12:12:33

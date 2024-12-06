-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: bicho_teste
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES (1,'cachorro'),(2,'gato'),(3,'outros');
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrinho`
--

DROP TABLE IF EXISTS `carrinho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrinho` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `produto_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrinho`
--

LOCK TABLES `carrinho` WRITE;
/*!40000 ALTER TABLE `carrinho` DISABLE KEYS */;
INSERT INTO `carrinho` VALUES (29,60,3);
/*!40000 ALTER TABLE `carrinho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'alimentacao'),(2,'transporte'),(3,'conforto'),(4,'roupas'),(5,'higiene'),(6,'aves'),(7,'peixes'),(8,'roedores');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favoritos`
--

DROP TABLE IF EXISTS `favoritos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favoritos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `produto_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoritos`
--

LOCK TABLES `favoritos` WRITE;
/*!40000 ALTER TABLE `favoritos` DISABLE KEYS */;
INSERT INTO `favoritos` VALUES (33,4,3,0),(36,69,3,NULL);
/*!40000 ALTER TABLE `favoritos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_item`
--

DROP TABLE IF EXISTS `pedido_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `imagem_produto` varchar(255) DEFAULT NULL,
  `nome_produto` varchar(255) DEFAULT NULL,
  `preco_produto` double DEFAULT NULL,
  `pedido_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6yyvsdnhpt133m120t65l215s` (`pedido_id`),
  CONSTRAINT `FK6yyvsdnhpt133m120t65l215s` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_item`
--

LOCK TABLES `pedido_item` WRITE;
/*!40000 ALTER TABLE `pedido_item` DISABLE KEYS */;
INSERT INTO `pedido_item` VALUES (1,'guia1.jpg','Guia colorida G',29.9,1),(2,'acessorio8.jpg','Bandana Scooby Doo',19.9,1),(3,'fonte4.jpg','Bebedouro fonte 2L',50,2),(4,'chapeu.jpg','Chapéu para coelho',9.9,3),(5,'comedouro3.jpg','Comedouro elevado',35,3),(6,'acessorio9.jpg','Bandana Grêmio',25.9,4),(7,'guia1.jpg','Guia colorida G',29.9,5);
/*!40000 ALTER TABLE `pedido_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `valor_total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,'Pagamento aprovado',3,63.3),(2,'Pagamento aprovado',3,50),(3,'Pagamento aprovado',3,44.9),(4,'Pagamento aprovado',3,39.4),(5,'Pagamento aprovado',3,43.4);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `imagem` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `animal_id` bigint DEFAULT NULL,
  `categoria_id` bigint DEFAULT NULL,
  `subcategoria_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrb7bphv997mjy1cam0rdta8ie` (`animal_id`),
  KEY `FKopu9jggwnamfv0c8k2ri3kx0a` (`categoria_id`),
  KEY `FKhagp5w2xvnw7fkbckag4ulo6b` (`subcategoria_id`),
  CONSTRAINT `FKhagp5w2xvnw7fkbckag4ulo6b` FOREIGN KEY (`subcategoria_id`) REFERENCES `subcategoria` (`id`),
  CONSTRAINT `FKopu9jggwnamfv0c8k2ri3kx0a` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`),
  CONSTRAINT `FKrb7bphv997mjy1cam0rdta8ie` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Escova rasqueadeira Chalesco auto limpante para cachorros','escova1.jpg','Escova Rasqueadeira',9.9,1,5,18),(2,'Bebedouro para cachorros de pelos longos pequeno rosa Pet Flex','bebedouro3.jpg','Bebedouro P',10,1,1,2),(3,'Guia para cachorro Midgnight Dog.u <br> 1,20m de comprimento, mosquetão com trava de segurança e acabamentos em liga de zinco.','guia1.jpg','Guia colorida G',29.9,1,2,6),(4,'Comedouro Vetreska em formato de laranja. <br> Material: ABS. Design ergonômico e inclinado.','comedouro4.jpg','Comedouro laranja',49.9,1,1,1),(5,'Coleira de couro vermelha pequena para cães. Produto regulável.','coleira3.jpg','Coleira couro P',8.9,1,2,4),(6,'Comedouro automático 8 litros para cães de grande porte.','comedouro1.jpg','Comedouro automático 8L',30,1,1,1),(7,'Porta ração dispenser Plaspet 18 litros','porta-racao5.jpg','Porta ração dispenser',180,1,1,3),(8,'Roupa de tricô rosa Hello Pets tamanho P. <br> Circunferância pescoço: 24 a 30cm. <br> Circunferência peito: 34 a 44cm <br> Comprimento corpo: 24cm','roupa-i4.jpg','Roupa de tricô P',56.9,1,4,14),(9,'Bandana temática Scooby Doo FreeFaro M. <br> Largura: 50cm. Altura: 25cm.','acessorio8.jpg','Bandana Scooby Doo',19.9,1,4,15),(10,'Roupa pós-cirurgica castração cães machos número 12 Pet Med. <br> Peso aproximado: 32 a 38kg.','roupa-c1.jpg','Roupa cirúrgica nº12',35.9,1,5,16),(11,'Coleira courino rosa Matelassê M (7 a 12kg)','coleira1.jpg','Coleira rosa M',18.9,1,2,4),(12,'Porta ração capacidade 2,4 litros. <br> Suporte com dois compartimentos.','porta-racao1.jpg','Porta ração 2,4L',15,1,1,3),(13,'Caixa de areia grande com grade','caixa-areia2.jpg','Caixa de areia G',28.9,2,5,19),(14,'Bebedouro para gatos fonte de água automática Petlon 2 litros 110v.','fonte4.jpg','Bebedouro fonte 2L',50,2,1,2),(15,'Gaiola para pássaro curió. <br> Comprimento: 48,5cm <br> Largura: 21,5cm <br> Altura: 49cm','gaiola-p1.jpg','Gaiola Curió',59.9,3,6,20),(16,'Aquário para peixe Betta 4,5 litros com decoração fixa. <br> CxLxA: 25cm x 12xm x 15cm','aquario.jpg','Aquário Betta',30,3,7,23),(17,'Gaiola porquinho da india, coelho e chichila 2 andares. <br> CxLxA: 55cm x 38xm x 42cm','gaiola-r2.jpg','Gaiola 2 andares',75,3,8,26),(18,'Gaiola casa para passarinho com argola, poleiro e comedouro <br> Comprimento: 35cm <br> Largura: 28cm <br>Altura: 46cm','gaiola-p2.jpg','Gaiola Passarinho',69.9,3,6,20),(19,'Gaiola de madeira para transporte de pássaros <br> Comprimento: 19,5cm <br> Largura: 21cm <br>Altura: 13cm','gaiola-p3.jpg','Gaiola transporte pássaro',20,3,6,20),(20,'Aquário de vidro 50 litros com tampa de vidro <br> Comprimento: 50cm <br> Largura: 30cm <br> Altura: 35cm','aquario2.jpg','Aquário 50L',100,3,7,23),(21,'Filtro Externo Hang On Ocean Tech 110v','filtro.jpg','Filtro externo',59.9,3,7,24),(22,'Gaiola de 4 andares para roedores de médio porte  <br> Comprimento: 79cm <br> Largura: 52,5cm <br> Altura: 141cm','gaiola-r1.jpg','Gaiola 4 andares',250,3,8,26),(23,'Toca casinha de madeira pequena para hamsters','toca1.jpg','Toca madeira',15.9,3,8,27),(24,'Cama ninho de corda para passarinhos pequenos','cama-p1.jpg','Cama ninho',9.9,3,6,21),(25,'Casa ninho de madeira para passarinhos  <br> Comprimento: 14,5cm <br> Largura: 12cm <br> Altura: 14cm','casinha-p1.jpg','Casa ninho',12.9,3,6,21),(26,'Alimentador automático para peixes com dosador inteligente','alimentador.jpg','Alimentador automático',60,3,7,25),(27,'Gaiola para hamster com tubos e roda de exercício  <br> Comprimento: 38cm <br> Largura: 24cm <br> Altura: 30cm','gaiola-r3.jpg','Gaiola Hamster',85.9,3,8,26),(28,'Bola de exercício para hamster 14cm','acessorio-r1.jpg','Bola para Hamster',5.9,3,8,27),(29,'Bebedouro para pássaros 300ml','bebedouro-p1.jpg','Bebedouro pássaros',5.9,3,6,22),(30,'Bebedouro para beija-flor 250ml','bebedouro-p2.jpg','Bebedouro beija-flor',7.9,3,6,22),(31,'Bomba de ar oxigenador compressor para aquários 220v','bomba1.jpg','Bomba compressora',19.9,3,7,24),(32,'Bomba submersa Boyu FP-58 2500L/h para aquários, fontes e cascatas <br> Circulação da água de aquários de água doce e salgada','bomba2.jpg','Bomba submersa',120,3,7,24),(33,'Toca casinha com rampa para hamster em madeira','casinha-r1.jpg','Toca casinha',12.9,3,8,27),(34,'Casinha toca soft para roedores  <br> Comprimento: 18cm <br> Largura: 18cm <br> Altura: 15cm','toca2.jpg','Casinha toca',22,3,8,27),(35,'Chapéu para coelhos em tecido soft','chapeu.jpg','Chapéu para coelho',9.9,3,8,27),(36,'Rodinha de hamster silenciosa de madeira 20cm de diâmetro','acessorio-r2.jpg','Roda para hamster',15.9,3,8,27),(37,'Cercado para porquinho da índia com toca e tecido soft  <br> Comprimento: 80cm <br> Largura: 40cm <br> Altura: 40cm','cercadinho.jpg','Cercadinho playground',90,3,8,27),(38,'Bebedouro para roedores 80ml','bebedouro-r1.jpg','Bebedouro',7.9,3,8,27),(39,'Comedouro para gatos com carinha - 60ml','comedouro-g1.jpg','Comedouro carinha',8.9,2,1,1),(40,'Comedouro de porcelana para gatos 250ml','comedouro-g2.jpg','Comedouro porcelana',15.9,2,1,1),(41,'Caixa de transporte rosa para gatos até 5kg','caixa-transporte-g1.jpg','Caixa transporte P',20,2,2,7),(42,'Cama de janela para gatos Catbed <br> Comprimento: 55cm <br> Largura: 34,5cm <br>','cama-g1.jpg','Cama de janela',35,2,3,10),(43,'Coleira reflexiva para gatos com fecho de segurança regulável até 31cm','coleira-g2.jpg','Coleira reflexiva',18.9,2,2,4),(44,'Coleira azul com estrelinhas e pingente inox','coleira-g1.jpg','Coleira estrelinhas',12.9,2,2,4),(45,'Bebedouro automático para gatos AquaPurr','fonte6.jpg','Bebedouro AquaPurr',40,2,1,2),(46,'Cama arranhadora de papelão ondulado para gatos até 5kg','cama-g2.jpg','Cama arranhadora',29.9,2,3,10),(47,'Caixa de areia para gatos com borda alta 20 litros','caixa-areia.jpg','Caixa de areia',13.9,2,5,19),(48,'Tapete higiênico coletor de areia para gatos \n <br> Comprimento: 51cm <br> Largura: 41cm <br> Altura: 3cm','tapete.jpg','Tapete higiênico',12.9,2,5,19),(49,'Caixa de transporte azul para gatos grandes até 12kg \n <br> Comprimento: 61cm <br> Largura: 40cm <br> Altura: 38cm','caixa-transporte-g2.jpg','Caixa de transporte G',55,2,2,7),(50,'Roupa cirúrgica para gatos de 1,7kg a 2,3kg - 4 a 6 meses','roupa-cg1.jpg','Roupa cirúrgica nº0',19.9,2,5,16),(51,'Roupa cirúrgica para gatos de 3,8kg a 5kg - Adultos médios','roupa-cg2.jpg','Roupa cirúrgica nº3',22.9,2,5,16),(52,'Casa para gato de madeira com abertura de gatinho e tampa com dobradiças\n <br> Comprimento: 40cm <br> Largura: 40cm <br> Altura: 45cm','casinha-g1.jpg','Casinha',49.9,2,3,9),(53,'Comedouro para gato elevado anti formiga 140ml','comedouro-g3.jpg','Comedouro elevado',10.9,2,1,1),(54,'Bebedouro para gatos tipo fonte de inox 3L','fonte5.jpg','Bebedouro fonte 3L',70,2,1,2),(55,'Pote porta ração para gatos de 3,6 litros','porta-racao-g1.jpg','Porta ração 3,6L',15,2,1,3),(56,'Coleira com pingente para gatos 29cm de comprimento','coleira-g3.jpg','Coleira pingente',10.9,2,2,4),(57,'Bebedouro fonte para gatos 1.2 litros','fonte2.jpg','Bebedouro fonte 1,2L',35,2,1,2),(58,'Escova Steamy Cat vaporizada para remoção de pelos emaranhados','escova-g1.jpg','Escova Steamy',15.9,2,5,18),(59,'Colar Elizabetano em tecido para gatos médios','colar-g1.jpg','Colar Elizabetano',15.9,2,5,17),(60,'Comedouro elevado para cachorros em porcelana e madeira','comedouro3.jpg','Comedouro elevado',35,1,1,1),(61,'Garrafa de água portátil para cachorros em viagem 450ml','bebedouro6.jpg','Garrafa água',9.9,1,1,2),(62,'Coleira peitoral altamente regulável para cães até 3kg','peitoral1.jpg','Coleira peitoral P',39.9,1,2,5),(63,'Guia retrátil 5m estica e volta para cachorros','guia3.jpg','Guia retrátil 5m',25,1,2,6),(64,'Casa de madeira para cachorros\n <br> Comprimento: 50cm <br> Largura: 45cm <br> Altura: 49cm','casinha3.jpg','Casinha madeira',35,1,3,9),(65,'Roupinha com estampa do Snoopy tamanho P','roupa-v1.jpg','Roupinha Snoopy',29.9,1,4,13),(66,'Roupinha de banho biquíni com estampa de melancia para cães pequenos','roupa-v3.jpg','Roupa de banho',39.9,1,4,13),(67,'Bolsa de transporte com tecido macio para cachorros\n <br> Comprimento: 43cm <br> Largura: 28cm <br> Altura: 28cm','bolsa-transporte.jpg','Bolsa transporte',70,1,2,8),(68,'Cama azul para cachorros impermeável Ecotec 63cm x 50 cm','cama3.jpg','Cama azul M',39.9,1,3,10),(69,'Bandana Grêmio dupla face bo.be Store','acessorio9.jpg','Bandana Grêmio',25.9,1,4,15),(70,'Colar Elizabetano para cachorros nº 7','colar1.jpg','Colar Elizabetano nº7',29.9,1,5,17);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategoria`
--

DROP TABLE IF EXISTS `subcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategoria` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategoria`
--

LOCK TABLES `subcategoria` WRITE;
/*!40000 ALTER TABLE `subcategoria` DISABLE KEYS */;
INSERT INTO `subcategoria` VALUES (1,'comedouros'),(2,'bebedouros'),(3,'acessorios_alimentacao'),(4,'coleiras'),(5,'peitorais'),(6,'guias'),(7,'caixas_transporte'),(8,'outros_transporte'),(9,'casas'),(10,'camas'),(11,'cobertores'),(12,'almofadas'),(13,'roupas_verao'),(14,'roupas_inverno'),(15,'acessorios_roupa'),(16,'roupas_cirurgicas'),(17,'colar_elizabetano'),(18,'escovas'),(19,'caixa_areia'),(20,'gaiolas_aves'),(21,'acessorios_aves'),(22,'bebedouros_aves'),(23,'aquarios'),(24,'filtros'),(25,'alimentador'),(26,'gaiolas_roedores'),(27,'acessorios_roedores');
/*!40000 ALTER TABLE `subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) NOT NULL,
  `data_nascimento` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `endereco_complemento` varchar(255) DEFAULT NULL,
  `endereco_numero` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `ponto_referencia` varchar(255) DEFAULT NULL,
  `senha` varchar(255) NOT NULL,
  `telefone` varchar(255) NOT NULL,
  `cep` varchar(255) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (3,'111.222.333-44','19/06/2001','bicho@teste','Av. Padre Leopoldo Brentano','','100','Bichó Teste','','teste','(21) 98765-4321','90250-590','Porto Alegre','RS');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-06 16:59:17

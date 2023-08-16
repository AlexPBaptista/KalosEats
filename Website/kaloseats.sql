-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 08-Maio-2023 às 09:02
-- Versão do servidor: 8.0.31
-- versão do PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `kaloseats`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `contasapp`
--

CREATE TABLE `contasapp` (
  `idConta` int NOT NULL,
  `nome` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `dono`
--

CREATE TABLE `dono` (
  `idDono` int NOT NULL,
  `primeiroNome` varchar(56) COLLATE utf8mb4_general_ci NOT NULL,
  `ultimoNome` varchar(56) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `dataNascimento` date DEFAULT NULL,
  `telefoneDono` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telemovel` varchar(15) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `dono`
--

INSERT INTO `dono` (`idDono`, `primeiroNome`, `ultimoNome`, `email`, `dataNascimento`, `telefoneDono`, `telemovel`) VALUES
(1, 'Leonardo', 'Cunha', 'leogre03@gmail.com', '2003-06-12', NULL, '914562857'),
(2, 'Alexandre', 'Baptista', 'restantenovamente@gmail.com', '2005-04-21', '253468474', '964397396');

-- --------------------------------------------------------

--
-- Estrutura da tabela `estabelecimentos`
--

CREATE TABLE `estabelecimentos` (
  `idEstabelecimento` int NOT NULL,
  `tipo` varchar(22) COLLATE utf8mb4_general_ci NOT NULL,
  `nomeEstabelecimento` varchar(52) COLLATE utf8mb4_general_ci NOT NULL,
  `morada` tinytext COLLATE utf8mb4_general_ci NOT NULL,
  `horaAbertura` time NOT NULL,
  `horaEncerro` time NOT NULL,
  `telefone` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `website` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `estabelecimentos`
--

INSERT INTO `estabelecimentos` (`idEstabelecimento`, `tipo`, `nomeEstabelecimento`, `morada`, `horaAbertura`, `horaEncerro`, `telefone`, `website`) VALUES
(1, 'Cafe, Bar', 'Impactus', 'R. 1 Lugar do Faial 55, 4730-458 Vila de Prado', '08:00:00', '05:00:00', NULL, NULL),
(2, 'Café Bar Restaurante ', 'Alexandes', 'Rua de novainho 71 Gualtar', '08:00:00', '19:00:00', '253147254', 'alexandes.com');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `contasapp`
--
ALTER TABLE `contasapp`
  ADD PRIMARY KEY (`idConta`);

--
-- Índices para tabela `dono`
--
ALTER TABLE `dono`
  ADD PRIMARY KEY (`idDono`);

--
-- Índices para tabela `estabelecimentos`
--
ALTER TABLE `estabelecimentos`
  ADD PRIMARY KEY (`idEstabelecimento`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `contasapp`
--
ALTER TABLE `contasapp`
  MODIFY `idConta` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `dono`
--
ALTER TABLE `dono`
  MODIFY `idDono` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `estabelecimentos`
--
ALTER TABLE `estabelecimentos`
  MODIFY `idEstabelecimento` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

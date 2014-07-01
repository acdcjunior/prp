-- DADOS USADOS NO AMBIENTE DE TESTES

INSERT INTO `TB_CATEGORIA` (`ID`, `NOME`, `REVISADO`) VALUES
(1, 'Despesas:Moradia:Aluguel', ''),
(3, 'Despesas:Moradia:Condomínio', ''),
(4, 'Ativos:Carteira', ''),
(6, 'Investimentos:Prestação Apartamento', ''),
(7, 'Despesas:Taxas Bancárias', ''),
(8, 'Receitas:Salário:Folha Mensal', ''),
(12, 'Ativos:Cartão Mastervisa', ''),
(17, 'Despesas:Compras', ''),
(22, 'Despesas:Outras', '');



INSERT INTO `tb_origem` (`ID`, `ALIAS`, `NOME`) VALUES
(1, 'bancocorrente', 'Conta-Corrente Banco'),
(2, 'mastervisa', 'Cartão de Crédito Mastervisa'),
(3, 'b2corrente', 'Conta-Corrente Banco2'),
(4, 'visamaster', 'Cartão de Crédito Visamaster');



INSERT INTO `tb_previsao` (`ID`, `DATA`, `DESCRICAO`, `VALOR`, `CATEGORIA`, `BILL`) VALUES
(1, '2011-01-20', 'SAQUE 2011-01', -500.00, 4, 0),
(2, '2011-01-31', 'SAQUE 2011-01-2', -100.00, 4, 0),
(54, '2011-02-28', 'SAQUE 2011-02', -500.00, 4, 0),
(55, '2011-03-31', 'SAQUE 2011-03', -500.00, 4, 0),
(56, '2011-04-30', 'SAQUE 2011-04', -500.00, 4, 0),
(57, '2011-05-31', 'SAQUE 2011-05', -500.00, 4, 0),
(58, '2011-06-30', 'SAQUE 2011-06', -500.00, 4, 0),
(59, '2011-07-31', 'SAQUE 2011-07', -500.00, 4, 0),
(60, '2011-08-31', 'SAQUE 2011-08', -500.00, 4, 0),
(61, '2011-09-30', 'SAQUE 2011-09', -500.00, 4, 0),
(62, '2011-10-31', 'SAQUE 2011-10', -500.00, 4, 0),
(63, '2011-11-30', 'SAQUE 2011-11', -500.00, 4, 0),
(64, '2011-12-31', 'SAQUE 2011-12', -500.00, 4, 0),
(101, '2011-01-25', 'FOLHA MENSAL 2011-01', 6300.00, 8, 0),
(102, '2011-02-25', 'FOLHA MENSAL 2011-02', 6300.00, 8, 0),
(103, '2011-03-25', 'FOLHA MENSAL 2011-03', 6300.00, 8, 0),
(104, '2011-04-25', 'FOLHA MENSAL 2011-04', 6300.00, 8, 0),
(105, '2011-05-25', 'FOLHA MENSAL 2011-05', 6300.00, 8, 0),
(106, '2011-06-25', 'FOLHA MENSAL 2011-06', 6300.00, 8, 0),
(107, '2011-07-25', 'FOLHA MENSAL 2011-07', 6300.00, 8, 0),
(108, '2011-08-25', 'FOLHA MENSAL 2011-08', 6300.00, 8, 0),
(109, '2011-09-25', 'FOLHA MENSAL 2011-09', 6300.00, 8, 0),
(110, '2011-10-25', 'FOLHA MENSAL 2011-10', 6300.00, 8, 0),
(111, '2011-11-25', 'FOLHA MENSAL 2011-11', 6300.00, 8, 0),
(112, '2011-12-25', 'FOLHA MENSAL 2011-12', 6300.00, 8, 0),
(281, '2011-01-31', 'TAXAS BANCARIAS 2011-01', 20.00, 7, 0),
(282, '2011-02-28', 'TAXAS BANCARIAS 2011-02', 20.00, 7, 0),
(283, '2011-03-31', 'TAXAS BANCARIAS 2011-03', 20.00, 7, 0),
(284, '2011-04-30', 'TAXAS BANCARIAS 2011-04', 20.00, 7, 0),
(285, '2011-05-31', 'TAXAS BANCARIAS 2011-05', 20.00, 7, 0),
(286, '2011-06-30', 'TAXAS BANCARIAS 2011-06', 20.00, 7, 0),
(287, '2011-07-31', 'TAXAS BANCARIAS 2011-07', 20.00, 7, 0),
(288, '2011-08-31', 'TAXAS BANCARIAS 2011-08', 20.00, 7, 0),
(289, '2011-09-30', 'TAXAS BANCARIAS 2011-09', 20.00, 7, 0),
(290, '2011-10-31', 'TAXAS BANCARIAS 2011-10', 20.00, 7, 0),
(291, '2011-11-30', 'TAXAS BANCARIAS 2011-11', 20.00, 7, 0),
(292, '2011-12-31', 'TAXAS BANCARIAS 2011-12', 20.00, 7, 0),
(316, '2011-12-26', 'CAMA', -3000.00, 17, 0),
(353, '2011-01-26', 'MASTERVISA 2011-01', -3000.00, 12, 1),
(354, '2011-02-26', 'MASTERVISA 2011-02', -3000.00, 12, 0),
(355, '2011-03-26', 'MASTERVISA 2011-03', -3000.00, 12, 0),
(356, '2011-04-26', 'MASTERVISA 2011-04', -3000.00, 12, 0),
(357, '2011-05-26', 'MASTERVISA 2011-05', -3000.00, 12, 0),
(358, '2011-06-26', 'MASTERVISA 2011-06', -3000.00, 12, 0),
(359, '2011-07-26', 'MASTERVISA 2011-07', -3000.00, 12, 0),
(360, '2011-08-26', 'MASTERVISA 2011-08', -3000.00, 12, 0),
(361, '2011-09-26', 'MASTERVISA 2011-09', -3000.00, 12, 0),
(362, '2011-10-26', 'MASTERVISA 2011-10', -3000.00, 12, 0),
(363, '2011-11-26', 'MASTERVISA 2011-11', -3000.00, 12, 0),
(364, '2011-12-26', 'MASTERVISA 2011-12', -3000.00, 12, 0),
(389, '2011-01-06', 'PREST APTO 2011-01', -1600.00, 6, 1),
(390, '2011-02-06', 'PREST APTO 2011-02', -1600.00, 6, 0),
(391, '2011-03-06', 'PREST APTO 2011-03', -1600.00, 6, 0),
(392, '2011-04-06', 'PREST APTO 2011-04', -1600.00, 6, 0),
(393, '2011-05-06', 'PREST APTO 2011-05', -1600.00, 6, 0),
(394, '2011-06-06', 'PREST APTO 2011-06', -1600.00, 6, 0),
(395, '2011-07-06', 'PREST APTO 2011-07', -1600.00, 6, 0),
(396, '2011-08-06', 'PREST APTO 2011-08', -1600.00, 6, 0),
(397, '2011-09-06', 'PREST APTO 2011-09', -1600.00, 6, 0),
(398, '2011-10-06', 'PREST APTO 2011-10', -1600.00, 6, 0),
(399, '2011-11-06', 'PREST APTO 2011-11', -1600.00, 6, 0),
(400, '2011-12-06', 'PREST APTO 2011-12', -1600.00, 6, 0),
(425, '2011-01-05', 'COND APTO 2011-01', -600.00, 3, 0),
(426, '2011-02-05', 'COND APTO 2011-02', -600.00, 3, 0),
(427, '2011-03-05', 'COND APTO 2011-03', -600.00, 3, 0),
(428, '2011-04-05', 'COND APTO 2011-04', -600.00, 3, 0),
(429, '2011-05-05', 'COND APTO 2011-05', -600.00, 3, 0),
(430, '2011-06-05', 'COND APTO 2011-06', -600.00, 3, 0),
(431, '2011-07-05', 'COND APTO 2011-07', -600.00, 3, 0),
(432, '2011-08-05', 'COND APTO 2011-08', -600.00, 3, 0),
(433, '2011-09-05', 'COND APTO 2011-09', -600.00, 3, 0),
(434, '2011-10-05', 'COND APTO 2011-10', -600.00, 3, 0),
(435, '2011-11-05', 'COND APTO 2011-11', -600.00, 3, 0),
(436, '2011-12-05', 'COND APTO 2011-12', -600.00, 3, 0),
(461, '2011-01-31', 'COMPRAS 2011-01', -1000.00, 17, 0),
(462, '2011-02-28', 'COMPRAS 2011-02', -1000.00, 17, 0),
(463, '2011-03-31', 'COMPRAS 2011-03', -1000.00, 17, 0),
(464, '2011-04-30', 'COMPRAS 2011-04', -1000.00, 17, 0),
(465, '2011-05-31', 'COMPRAS 2011-05', -1000.00, 17, 0),
(466, '2011-06-30', 'COMPRAS 2011-06', -1000.00, 17, 0),
(467, '2011-07-31', 'COMPRAS 2011-07', -1000.00, 17, 0),
(468, '2011-08-31', 'COMPRAS 2011-08', -1000.00, 17, 0),
(469, '2011-09-30', 'COMPRAS 2011-09', -1000.00, 17, 0),
(470, '2011-10-31', 'COMPRAS 2011-10', -1000.00, 17, 0),
(471, '2011-11-30', 'COMPRAS 2011-11', -1000.00, 17, 0),
(472, '2011-12-31', 'COMPRAS 2011-12', -1000.00, 17, 0),
(497, '2011-01-31', 'OUTROS 2011-01', -1000.00, 22, 0),
(498, '2011-02-28', 'OUTROS 2011-02', -1000.00, 22, 0),
(499, '2011-03-31', 'OUTROS 2011-03', -1000.00, 22, 0),
(500, '2011-04-30', 'OUTROS 2011-04', -1000.00, 22, 0),
(501, '2011-05-31', 'OUTROS 2011-05', -1000.00, 22, 0),
(502, '2011-06-30', 'OUTROS 2011-06', -1000.00, 22, 0),
(503, '2011-07-31', 'OUTROS 2011-07', -1000.00, 22, 0),
(504, '2011-08-31', 'OUTROS 2011-08', -1000.00, 22, 0),
(505, '2011-09-30', 'OUTROS 2011-09', -1000.00, 22, 0),
(506, '2011-10-31', 'OUTROS 2011-10', -1000.00, 22, 0),
(507, '2011-11-30', 'OUTROS 2011-11', -1000.00, 22, 0),
(508, '2011-12-31', 'OUTROS 2011-12', -1000.00, 22, 0);



INSERT INTO `TB_MOVIMENTACAO`
(`ID`, `DATA`,		 `NUMERODOCUMENTO`,	`DESCRICAO1`,						`DESCRICAO2`,	`DESCRICAO3`,	`VALOR`,	`ORIGEM`,	`SALDO`,	`REALIZA`,	`ANTERIOR`) VALUES
(1, '2010-01-03',	 '0',			'MULTA JUROS CH ESP',					NULL,			NULL,			-6.95,		1,			1613.65,	281,		NULL),
(2, '2011-06-01',	 '123',			'PAGAMENTO FATURA ANTERIOR',					NULL,			NULL,			1280.52,		2,			0,	NULL,		NULL),
(3, '2011-06-12',	 '569',			'COMPRA VIA CC COMIDA',					NULL,			NULL,			172.42,		2,			172.42,	NULL,		2),
(10, '2010-03-01',	 '2',			'BOLATO PRIMEIRO DIA DO MES',			NULL,			NULL,			-40.00,		1,			19960.00,	NULL,		1),
(11, '2010-03-10',	 '3',			'BOLATO DECIMO DIA DO MES',				NULL,			NULL,			-50.77,		1,			613.59,		NULL,		10),
(12, '2010-03-25',	 '4',			'BOLATO ULTIMO DIA DO MES',				NULL,			NULL,			1000.06,	1,			1613.65,	NULL,		11),

(178, '2011-01-03',	 '5',	 		'BOLATO: PRIMEIRA DE 2011/01',			NULL,			NULL,			-68.61,		1,			1545.04,	461,		12),
(177, '2011-01-03',	 '5',	 		'BOLATO - CONDOMINIO 2011-01',			NULL,			NULL,			-510.00,	1,			1035.04,	425,		178),

(179, '2011-01-03',	 '6',	 		'BOLATO',								NULL,			NULL,			-1200.89,	1,			-165.85,	497,		177),
(180, '2011-01-03',	 '7',			'BOLATO: JCUERVO',						NULL,			NULL,			-81.00,		1,			-246.85,	461,		179),
(181, '2011-01-03',	 '8',			'BOLATO TELEFONE 2011.01.01 FIXO',		NULL,			NULL,			-4.11,		1,			-250.96,	NULL,		180),
(182, '2011-01-03',	 '9',			'BOLATO TELEFONE 2011.01.01 MOBI',		NULL,			NULL,			-65.79,		1,			-316.75,	NULL,		181),
(183, '2011-01-03',	 '9',			'BOLATO ENERGIA - 2011-01',				NULL,			NULL,			-32.04,		1,			-348.79,	NULL,			182),

(185, '2011-01-03',	 '1',			'MULTA JUROS CH ESP',					NULL,			NULL,			-14.01,		1,			-362.8,		281,		183),
(184, '2011-01-04',	 '2',			'ATM SAQUE',							NULL,			NULL,			-148.00,	1,			-510.8,		NULL,		185),

(186, '2011-01-04',	 '3',			'ATM SAQUE',							NULL,			NULL,			-98.00,		1,			-608.8,		NULL,		184),
(187, '2011-01-04',	 '4',			'ATM SAQUE',							NULL,			NULL,			-148.00,	1,			-756.8,		NULL,		186),
(188, '2011-01-04',	 '3',			'BOLATO: VODKAS',						NULL,			NULL,			-54.70,		1,			-811.5,		461,		187),
(189, '2011-01-04',	 '6',			'BOLATO TELEFONE 2010.12.26 MOBI',		NULL,			NULL,			-153.33,	1,			-964.83,	NULL,		188),
(190, '2011-01-05',	 '7',			'COMPRA DEBATO - TAMBOR',				NULL,			NULL,			-270.00,	1,			-1234.83,	461,		189),
(191, '2011-01-06',	 '4',			'INVESTIMENTO - 2011-01',				NULL,			NULL,			-1624.37,	1,			-2859.2,	389,		190),
(192, '2011-01-10',	 '0',			'TARIFA BANCO',							NULL,			NULL,			-22.00,		1,			-2881.2,	281,		191),
(193, '2011-01-10',	 '1',			'SALARIO',								NULL,			NULL,			6490.81,	1,			3609.61,	NULL,		192),
(194, '2011-01-10',	 '2',			'SALARIO - 2011-01-10',					NULL,			NULL,			2711.78,	1,			6321.39,	101,		193),
(195, '2011-01-14',	 '2',			'TRANSFERENCIA DOC - VIAGEM',			NULL,			NULL,			399.00,		1,			6720.39,	NULL,		194),
(196, '2011-01-17',	 '3',			'COMPRA DEBATO',						NULL,			NULL,			-7.32,		1,			6713.07,	461,		195),
(197, '2011-01-18',	 '3',			'BOLATO - STROBOS',						NULL,			NULL,			-131.00,	1,			6582.07,	461,		196),
(198, '2011-01-18',	 '3',			'BOLATO - COPOS COZA',					NULL,			NULL,			-105.01,	1,			6477.06,	461,		197),
(199, '2011-01-19',	 '4',			'ATM SAQUE',							NULL,			NULL,			-198.00,	1,			6279.06,	NULL,		198),
(200, '2011-01-24',	 '4',			'COMPRA DEBATO - BOZO LANCHES',			NULL,			NULL,			-20.20,		1,			6258.86,	461,		199),
(201, '2011-01-24',	 '4',			'ATM SAQUE',							NULL,			NULL,			-230.00,	1,			6028.86,	NULL,		200),
(202, '2011-01-25',	 '5',			'TRANSFERENCIA: CAMAROTE AJU',			NULL,			NULL,			-108.00,	1,			5920.86,	NULL,		201),
(203, '2011-01-25',	 '5',			'BOLATO TELEFONE 2011.01.26 MOBI',		NULL,			NULL,			-177.22,	1,			5743.64,	NULL,		202),
(204, '2011-01-25',	 '5',			'BOLATO - CREDITO 2011-01 JAN 2011',	NULL,			NULL,			-3408.26,	1,			2335.38,	353,		203),

(206, '2011-01-25',	 '6',			'SALARIO - 2011-01-25',					NULL,			NULL,			3475.54,	1,			5810.92,	101,		204),
(205, '2011-02-01',	 '0',			'MULTA JUROS CH ESP',					NULL,			NULL,			-11.43,		1,			5799.49,	282,		206),

(207, '2011-02-01',	 '6',			'MULTA JUROS CH ESP',					NULL,			NULL,			-29.43,		1,			5770.06,	282,		205),
(208, '2011-02-03',	 '6',			'BOLATO - CONDOMINIO 2011-02',			NULL,			NULL,			-510.00,	1,			5260.06,	426,		207),
(209, '2011-02-03',	 '7',			'BOLATO ENERGIA - 2011-02',				NULL,			NULL,			-31.78,		1,			5228.28,	NULL,		208),
(210, '2011-02-04',	 '7',			'COMPRA DEBATO - MAKRO LOJA 43',		NULL,			NULL,			-172.77,	1,			5055.51,	462,		209),
(211, '2011-02-07',	 '7',			'INVESTIMENTO - 2011-02',				NULL,			NULL,			-1625.38,	1,			3430.13,	390,		210),
(212, '2011-02-10',	 '0',			'TARIFA BANCO',							NULL,			NULL,			-22.00,		1,			3408.13,	282,		211),
(213, '2011-02-10',	 '8',			'SALARIO - 2011-02-10',					NULL,			NULL,			2671.40,	1,			6079.53,	102,		212),
(214, '2011-02-14',	 '8',			'ATM SAQUE',							NULL,			NULL,			-330.00,	1,			5749.53,	54,			213),
(215, '2011-02-21',	 '8',			'SALARIO',								NULL,			NULL,			4359.54,	1,			10109.07,	NULL,		214),
(216, '2011-02-21',	 '9',			'ATM SAQUE',							NULL,			NULL,			-230.00,	1,			9879.07,	54,			215),
(217, '2011-02-21',	 '9',			'BOLATO',								NULL,			NULL,			-493.24,	1,			9385.83,	462,		216),
(218, '2011-02-21',	 '9',			'BOLATO - CREDITO 2011-02 FEV 2011',	NULL,			NULL,			-4453.40,	1,			4932.43,	354,		217),
(219, '2011-02-21',	 '1',			'BOLATO TELEFONE 2011.02.01 CEL Oi',	NULL,			NULL,			-2.23,		1,			4930.2,		NULL,		218),
(220, '2011-02-21',	 '1',			'BOLATO TELEFONE 2011.02.01 FIXO',		NULL,			NULL,			-67.84,		1,			4862.36,	NULL,		219),
(221, '2011-02-22',	 '2',			'BOLATO - SQUASH DOIS E FRETE',			NULL,			NULL,			-43.00,		1,			4819.36,	462,		220),
(222, '2011-02-22',	 '1',			'BOLATO - PORTA LIXO',					NULL,			NULL,			-25.50,		1,			4793.86,	462,		221),
(223, '2011-02-22',	 '2',			'BOLATO - INTERRUPTOR REMOTO',			NULL,			NULL,			-65.00,		1,			4728.86,	462,		222),
(224, '2011-02-22',	 '3',			'BOLATO - CTROLE REMOTO',				NULL,			NULL,			-39.99,		1,			4688.87,	462,		223),
(225, '2011-02-22',	 '2',			'BOLATO - SQUASH UM',					NULL,			NULL,			-28.00,		1,			4660.87,	462,		224),
(226, '2011-02-24',	 '3',			'BOLATO',								NULL,			NULL,			-1367.28,	1,			3293.59,	498,		225),
(227, '2011-02-25',	 '3',			'SALARIO - 2011-02-25',					NULL,			NULL,			3470.71,	1,			6764.3,		102,		226),
(228, '2011-02-25',	 '4',			'COMPRA DEBATO - FERIAS',				NULL,			NULL,			-400.00,	1,			6364.3,		462,		227),
(229, '2011-02-28',	 '4',			'BOLATO - CONDOMINIO 2011-03',			NULL,			NULL,			-520.00,	1,			5844.3,		427,		228),
(230, '2011-02-28',	 '4',			'ORGAO DE CLASSE',						NULL,			NULL,			-1.71,		1,			5842.59,	NULL,		229),
(231, '2011-03-04',	 '5',			'ATM SAQUE',							NULL,			NULL,			-230.00,	1,			5612.59,	55,			230),
(232, '2011-03-09',	 '5',			'INVESTIMENTO - 2011-03',				NULL,			NULL,			-1623.08,	1,			3989.51,	391,		231),
(233, '2011-03-09',	 '5',			'ATM SAQUE',							NULL,			NULL,			-80.00,		1,			3909.51,	55,			232),
(234, '2011-03-10',	 '2',			'BOLATO ENERGIA - 2011-03',				NULL,			NULL,			-32.06,		1,			3877.45,	NULL,		233),
(235, '2011-03-10',	 '2',			'BOLATO TELEFONE 2011.02.25 MOBI',		NULL,			NULL,			-203.31,	1,			3674.14,	NULL,		234),
(236, '2011-03-10',	 '0',			'TARIFA BANCO',							NULL,			NULL,			-22.00,		1,			3652.14,	283,		235),
(237, '2011-03-10',	 '2',			'SALARIO - 2011-03-10',					NULL,			NULL,			2619.91,	1,			6272.05,	103,		236),
(238, '2011-03-14',	 '1',			'BOLATO TELEFONE 2011.03.01 FIXO',		NULL,			NULL,			-65.88,		1,			6206.17,	NULL,		237),
(239, '2011-03-22',	 '1',			'ATM SAQUE',							NULL,			NULL,			-230.00,	1,			5976.17,	55,			238),
(240, '2011-03-24',	 '2',			'BOLATO - CREDITO 2011-03 MAR 2011',	NULL,			NULL,			-3832.00,	1,			2144.17,	355,		239),
(241, '2011-03-25',	 '9',			'SALARIO - 2011-03-25',					NULL,			NULL,			3319.88,	1,			5464.05,	103,		240),
(242, '2011-04-01',	 '9',			'BOLATO - BOZO SHOP',					NULL,			NULL,			-315.00,	1,			5149.05,	500,		241),
(243, '2011-04-04',	 '9',			'ATM SAQUE',							NULL,			NULL,			-198.00,	1,			4951.05,	56,			242),
(244, '2011-04-04',	 '8',			'ATM SAQUE',							NULL,			NULL,			-98.00,		1,			4853.05,	56,			243),
(245, '2011-04-04',	 '8',			'BOLATO - CONDOMINIO 2011-04',			NULL,			NULL,			-520.00,	1,			4333.05,	428,		244),
(246, '2011-04-04',	 '8',			'BOLATO - STUDY',						NULL,			NULL,			-63.00,		1,			4270.05,	NULL,		245),
(247, '2011-04-06',	 '7',			'INVESTIMENTO - 2011-04',				NULL,			NULL,			-1620.80,	1,			2649.25,	392,		246),
(248, '2011-04-06',	 '7',			'TRANSFERENCIA - BRINCOS',				NULL,			NULL,			-50.00,		1,			2599.25,	NULL,		247),
(249, '2011-04-11',	 '7',			'BOLATO TELEFONE 2011.03.25 MOBI',		NULL,			NULL,			-160.63,	1,			2438.62,	NULL,		248),
(250, '2011-04-11',	 '0',			'TARIFA BANCO',							NULL,			NULL,			-22.00,		1,			2416.62,	284,		249),
(251, '2011-04-11',	 '6',			'SALARIO - 2011-04-10',					NULL,			NULL,			2617.43,	1,			5034.05,	104,		250),
(252, '2011-04-13',	 '6',			'ATM SAQUE',							NULL,			NULL,			-130.00,	1,			4904.05,	56,			251),
(253, '2011-04-13',	 '6',			'ATM SAQUE',							NULL,			NULL,			-80.00,		1,			4824.05,	56,			252),
(254, '2011-04-13',	 '5',			'BOLATO TELEFONE 2011.04.01 FIXO',		NULL,			NULL,			-69.75,		1,			4754.3,		NULL,		253),
(255, '2011-04-14',	 '5',			'BOLATO ENERGIA - 2011-04',				NULL,			NULL,			-32.85,		1,			4721.45,	NULL,		254),
(256, '2011-04-15',	 '5',			'BOLATO:ESTUDOS - 5 TONERS',			NULL,			NULL,			-789.55,	1,			3931.9,		NULL,		255),
(257, '2011-04-15',	 '4',			'BOLATO:ESTUDOS - 4 REFIS TONER',		NULL,			NULL,			-99.57,		1,			3832.33,	NULL,		256),
(258, '2011-04-25',	 '4',			'BOLATO - CREDITO 2011-04 ABR 2011',	NULL,			NULL,			-2716.32,	1,			1116.01,	356,		257),
(259, '2011-04-25',	 '4',			'SALARIO - 2011-04-25',					NULL,			NULL,			3569.07,	1,			4685.08,	104,		258),
(260, '2011-04-26',	 '3',			'BOLATO - CONDOMINIO 2011-05',			NULL,			NULL,			-520.00,	1,			4165.08,	429,		259),
(261, '2011-04-26',	 '3',			'BOLATO - COMPUTADOR',					NULL,			NULL,			-173.56,	1,			3991.52,	464,		260),
(262, '2011-04-29',	 '3',			'DIARIAS TRABALHO',						NULL,			NULL,			416.00,		1,			4407.52,	NULL,		261),
(263, '2011-04-29',	 '2',			'PAG DARF - IMPOSTO DE RENDA 2011',		NULL,			NULL,			-400.21,	1,			4007.31,	NULL,		262),
(264, '2011-05-02',	 '2',			'TRANSFERENCIA: SEI LA?',				NULL,			NULL,			-1000.00,	1,			3007.31,	501,		263),
(265, '2011-05-03',	 '2',			'ATM SAQUE',							NULL,			NULL,			-98.00,		1,			2909.31,	57,			264),
(266, '2011-05-03',	 '1',			'ATM SAQUE',							NULL,			NULL,			-98.00,		1,			2811.31,	57,			265),
(267, '2011-05-06',	 '1',			'INVESTIMENTO - 2011-05',				NULL,			NULL,			-1618.68,	1,			1192.63,	393,		266),
(268, '2011-05-10',	 '0',			'TARIFA BANCO',							NULL,			NULL,			-22.00,		1,			1170.63,	285,		267),
(269, '2011-05-10',	 '1',			'BOLATO TELEFONE 2011.04.25 MOBI',		NULL,			NULL,			-361.00,	1,			809.63,		NULL,		268),
(270, '2011-05-10',	 '2',			'SALARIO',								NULL,			NULL,			2779.01,	1,			3588.64,	105,		269),
(271, '2011-05-12',	 '9',			'BOLATO ENERGIA - 2011-05',				NULL,			NULL,			-33.08,		1,			3555.56,	NULL,		270),
(272, '2011-05-12',	 '9',			'BOLATO TELEFONE 2011.05.01 FIXO',		NULL,			NULL,			-65.79,		1,			3489.77,	NULL,		271),
(273, '2011-05-23',	 '8',			'TRANSFERENCIA: SEI LA?',				NULL,			NULL,			1000.00,	1,			4489.77,	501,		272),
(274, '2011-05-23',	 '8',			'BINGO',								NULL,			NULL,			-4.00,		1,			4485.77,	NULL,		273),
(275, '2011-05-23',	 '7',			'BINGO',								NULL,			NULL,			-2.00,		1,			4483.77,	NULL,		274),
(276, '2011-05-23',	 '7',			'BOLATO: CREDITO 2011-05',				NULL,			NULL,			-3093.33,	1,			1390.44,	357,		275),
(277, '2011-05-23',	 '6',			'BOLATO TELEFONE 2011.05.25 MOBI',		NULL,			NULL,			-233.21,	1,			1157.23,	NULL,		276),
(278, '2011-05-25',	 '6',			'BOLATO',								NULL,			NULL,			-986.41,	1,			170.82,		501,		277),
(279, '2011-05-25',	 '5',			'SALARIO',								NULL,			NULL,			3262.86,	1,			3433.68,	105,		278),
(280, '2011-05-30',	 '5',			'BOLATO - COND 2011.06.05',				NULL,			NULL,			-520.00,	1,			2913.68,	430,		279),
(281, '2011-05-30',	 '4',			'BOLATO ENERGIA - 2011-06',				NULL,			NULL,			-49.11,		1,			2864.57,	NULL,		280),
(282, '2011-06-01',	 '4',			'ATM SAQUE',							NULL,			NULL,			-98.00,		1,			2766.57,	58,			281),
(283, '2011-06-02',	 '6',			'COMPRA DEBATO',						NULL,			NULL,			-26.18,		1,			2740.39,	466,		282),
(284, '2011-06-02',	 '7',			'ATM SAQUE',							NULL,			NULL,			-48.00,		1,			2692.39,	58,			283),
(285, '2011-06-02',	 '7',			'ATM SAQUE',							NULL,			NULL,			-48.00,		1,			2644.39,	58,			284),
(286, '2011-06-02',	 '6',			'BINGO',								NULL,			NULL,			-28.00,		1,			2616.39,	NULL,		285),
(287, '2011-06-03',	 '6',			'TRANSFERENCIA DOC',					NULL,			NULL,			416.00,		1,			3032.39,	NULL,		286),
(288, '2011-06-06',	 '6',			'INVESTIMENTO - 2011-06',				NULL,			NULL,			-1616.79,	1,			1415.6,		394,		287),
(289, '2011-06-06',	 '5',			'COMPRA DEBATO',						NULL,			NULL,			-7.80,		1,			1407.8,		466,		288),
(290, '2011-06-10',	 '0',			'TARIFA BANCO',							NULL,			NULL,			-22.00,		1,			1385.8,		286,		289),
(291, '2011-06-10',	 '8',			'SALARIO',								NULL,			NULL,			2810.39,	1,			4196.19,	106,		290),
(292, '2011-06-13',	 '6',			'BOLATO',								NULL,			NULL,			-55.00,		1,			4141.19,	502,		291),
(293, '2011-06-24',	 '5',			'COMPRA DEBATO',						NULL,			NULL,			-37.86,		1,			4103.33,	466,		292),
(294, '2011-06-24',	 '7',			'SALARIO',								NULL,			NULL,			3408.10,	1,			7511.43,	106,		293),
(295, '2011-06-27',	 '6',			'COMPRA DEBATO',						NULL,			NULL,			-26.84,		1,			7484.59,	466,		294),
(296, '2011-06-27',	 '7',			'BOLATO: CREDITO 2011-06',				NULL,			NULL,			-2900.80,	1,			4583.79,	358,		295),
(297, '2011-06-27',	 '6',			'BOLATO TELEFONE 2011.06.25 MOBI',		NULL,			NULL,			-155.92,	1,			4427.87,	NULL,		296),
(298, '2011-06-27',	 '3',			'RECARCA MOBI',							NULL,			NULL,			-18.00,		1,			4409.87,	502,		297),
(299, '2011-06-29',	 '3',			'BOLATO TELEFONE 2011.06.01 FIXO',		NULL,			NULL,			-79.01,		1,			4330.86,	NULL,		298),
(300, '2011-06-29',	 '2',			'BOLATO TELEFONE 2011.07.01 FIXO',		NULL,			NULL,			-70.69,		1,			4260.17,	NULL,		299),
(301, '2011-06-29',	 '2',			'BOLATO - COND 2011.07.05',				NULL,			NULL,			-520.00,	1,			3740.17,	431,		300),
(302, '2011-07-06',	 '8',			'INVESTIMENTO - 2011-07',				NULL,			NULL,			-1615.19,	1,			2124.98,	395,		301),
(303, '2011-07-07',	 '8',			'TRANSFERENCIA DOC',					NULL,			NULL,			424.00,		1,			2548.98,	NULL,		302),
(304, '2011-07-08',	 '9',			'ATM SAQUE',							NULL,			NULL,			-100.00,	1,			2448.98,	59,			303),
(305, '2011-07-11',	 '0',			'TARIFA BANCO',							NULL,			NULL,			-22.00,		1,			2426.98,	287,		304),
(306, '2011-07-11',	 '8',			'ATM SAQUE',							NULL,			NULL,			-98.00,		1,			2328.98,	59,			305),
(307, '2011-07-11',	 '7',			'ATM SAQUE',							NULL,			NULL,			-148.00,	1,			2180.98,	59,			306),
(308, '2011-07-11',	 '6',			'COMPRA DEBATO',						NULL,			NULL,			-24.82,		1,			2156.16,	467,		307),
(309, '2011-07-11',	 '7',			'BOLATO: CREDITO 2011.07.11',			NULL,			NULL,			-3000.00,	1,			-843.84,	359,		308),
(310, '2011-07-11',	 '7',			'SALARIO',								NULL,			NULL,			2201.79,	1,			1357.95,	107,		309),
(311, '2011-07-11',	 '6',			'SALARIO',								NULL,			NULL,			10822.51,	1,			12180.46,	NULL,		310),
(312, '2011-07-14',	 '8',			'COMPRA DEBATO',						NULL,			NULL,			-14.00,		1,			12166.46,	467,		311),
(313, '2011-07-14',	 '8',			'COMPRA DEBATO',						NULL,			NULL,			-12.10,		1,			12154.36,	467,		312),
(314, '2011-07-14',	 '6',			'BOLATO: CREDITO 2011.07.26',			NULL,			NULL,			-3000.00,	1,			9154.36,	359,		313),
(315, '2011-07-15',	 '5',			'COMPRA DEBATO',						NULL,			NULL,			-44.22,		1,			9110.14,	467,		314),
(316, '2011-07-18',	 '9',			'COMPRA DEBATO',						NULL,			NULL,			-24.72,		1,			9085.42,	467,		315),
(317, '2011-07-18',	 '5',			'COMPRA DEBATO',						NULL,			NULL,			-4.75,		1,			9080.67,	467,		316),
(318, '2011-07-19',	 '9',			'ATM SAQUE',							NULL,			NULL,			-98.00,		1,			8982.67,	59,			317),
(319, '2011-07-19',	 '4',			'ATM SAQUE',							NULL,			NULL,			-98.00,		1,			8884.67,	59,			318),
(320, '2011-07-21',	 '9',			'ATM SAQUE',							NULL,			NULL,			-98.00,		1,			8786.67,	59,			319),
(321, '2011-07-25',	 '3',			'ATM SAQUE',							NULL,			NULL,			-100.00,	1,			8686.67,	59,			320),
(322, '2011-07-25',	 '8',			'ATM SAQUE',							NULL,			NULL,			-100.00,	1,			8586.67,	59,			321),
(323, '2011-07-25',	 '3',			'SALARIO',								NULL,			NULL,			1998.93,	1,			10585.6,	107,		322),
(324, '2011-07-26',	 '1',			'BOLATO ENERGIA - 2011-07',				NULL,			NULL,			-35.65,		1,			10549.95,	NULL,		323),
(325, '2011-07-26',	 '2',			'BOLATO: CREDITO 2011.07.26',			NULL,			NULL,			-1296.37,	1,			9253.58,	359,		324),
(326, '2011-07-28',	 '1',			'COMPRA DEBATO',						NULL,			NULL,			-30.72,		1,			9222.86,	467,		325),
(327, '2012-01-29',	 '2',			'MOCK',									NULL,			NULL,			112.06,		1,			22499.17,	NULL,		326);

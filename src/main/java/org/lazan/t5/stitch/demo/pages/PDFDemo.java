package org.lazan.t5.stitch.demo.pages;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.Response;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class PDFDemo {
	@Property
	private CountryStats stats;
	
	@Inject
	private ComponentResources componentResources;

	public static class CountryStats {
		public int rank;
		public String country;
		public int population;

		public CountryStats(int rank, String country, int population) {
			super();
			this.rank = rank;
			this.country = country;
			this.population = population;
		}
	}

	StreamResponse onChart() {
		DefaultKeyedValues values = new DefaultKeyedValues();
		CountryStats[] countryStats = getCountryStats();
		for (int i = 0; i < 10; ++ i) {
			CountryStats current = countryStats[i];
			values.addValue(current.country, current.population);
		}
		PieDataset pieDataset = new DefaultPieDataset(values);

		PiePlot3D plot = new PiePlot3D(pieDataset);
		plot.setForegroundAlpha(0.7f);
		plot.setDepthFactor(0.1);
		plot.setCircular(true);

		final JFreeChart chart = new JFreeChart(plot);
		chart.removeLegend();
		chart.setTitle("Top 10 Country Populations");

		return new StreamResponse() {
			public String getContentType() {
				return "image/png";
			}

			public InputStream getStream() throws IOException {
				BufferedImage image = chart.createBufferedImage(400, 370);
				ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
				ChartUtilities.writeBufferedImageAsPNG(byteArray, image);
				return new ByteArrayInputStream(byteArray.toByteArray());
			}

			public void prepareResponse(Response response) {
			}
		};
	}

	public String getChartURI() {
		return componentResources.createEventLink("chart").toAbsoluteURI();
	}

	/**
	 * Data from http://en.wikipedia.org/wiki/List_of_countries_by_population
	 * 
	 * @return
	 */
	@Cached
	public CountryStats[] getCountryStats() {
		return new CountryStats[] { new CountryStats(1, "China", 1347350000), new CountryStats(2, "India", 1210193422),
				new CountryStats(3, "United States", 314814000), new CountryStats(4, "Indonesia", 237641326),
				new CountryStats(5, "Brazil", 193946886), new CountryStats(6, "Pakistan", 181301000),
				new CountryStats(7, "Nigeria", 166629000), new CountryStats(8, "Bangladesh", 152518015),
				new CountryStats(9, "Russia", 143228300), new CountryStats(10, "Japan", 127530000),
				new CountryStats(11, "Mexico", 112336538), new CountryStats(12, "Philippines", 92337852),
				new CountryStats(13, "Vietnam", 87840000), new CountryStats(14, "Ethiopia", 84320987),
				new CountryStats(15, "Egypt", 82868000), new CountryStats(16, "Germany", 81857000),
				new CountryStats(17, "Iran", 75149669), new CountryStats(18, "Turkey", 74724269),
				new CountryStats(19, "Democratic Republic of the Congo", 69575000), new CountryStats(20, "Thailand", 65479453),
				new CountryStats(21, "France", 65350000), new CountryStats(22, "United Kingdom", 62262000),
				new CountryStats(23, "Italy", 60849247), new CountryStats(24, "South Africa", 51770560),
				new CountryStats(25, "South Korea", 50004441), new CountryStats(26, "Myanmar", 48724000),
				new CountryStats(27, "Colombia", 46797000), new CountryStats(28, "Spain", 46163116),
				new CountryStats(29, "Ukraine", 45560272), new CountryStats(30, "Tanzania", 43188000),
				new CountryStats(31, "Argentina", 40117096), new CountryStats(32, "Kenya", 38610097),
				new CountryStats(33, "Poland", 38538447), new CountryStats(34, "Algeria", 37100000),
				new CountryStats(35, "Canada", 34986500), new CountryStats(36, "Uganda", 34131400),
				new CountryStats(37, "Iraq", 33330000), new CountryStats(38, "Morocco", 32737200),
				new CountryStats(39, "Sudan", 30894000), new CountryStats(40, "Peru", 30135875),
				new CountryStats(41, "Malaysia", 29564000), new CountryStats(42, "Uzbekistan", 29123400),
				new CountryStats(43, "Venezuela", 28946101), new CountryStats(44, "Saudi Arabia", 28376355),
				new CountryStats(45, "Nepal", 26620809), new CountryStats(46, "Afghanistan", 25500100),
				new CountryStats(47, "Ghana", 24658823), new CountryStats(48, "North Korea", 24554000),
				new CountryStats(49, "Yemen", 24527000), new CountryStats(50, "Mozambique", 23700715),
				new CountryStats(51, "Taiwan[9]", 23268372), new CountryStats(52, "Australia", 22820084),
				new CountryStats(53, "Syria", 21846000), new CountryStats(54, "Ivory Coast", 21395000),
				new CountryStats(55, "Madagascar", 20696070), new CountryStats(56, "Angola", 20609294),
				new CountryStats(57, "Sri Lanka", 20277597), new CountryStats(58, "Cameroon", 19406100),
				new CountryStats(59, "Romania", 19042936), new CountryStats(60, "Kazakhstan", 16815000),
				new CountryStats(61, "Netherlands", 16765558), new CountryStats(62, "Chile", 16572475),
				new CountryStats(63, "Niger", 16274738), new CountryStats(64, "Malawi", 15883000),
				new CountryStats(65, "Burkina Faso", 15730977), new CountryStats(66, "Guatemala", 14713763),
				new CountryStats(67, "Mali", 14528662), new CountryStats(68, "Ecuador", 14483499),
				new CountryStats(69, "Cambodia", 14478000), new CountryStats(70, "Zambia", 13092666),
				new CountryStats(71, "Zimbabwe", 13014000), new CountryStats(72, "Senegal", 12855153),
				new CountryStats(73, "Chad", 11274106), new CountryStats(74, "Cuba", 11247925),
				new CountryStats(75, "Belgium", 10839905), new CountryStats(76, "Guinea", 10824200),
				new CountryStats(77, "Greece", 10787690), new CountryStats(78, "Rwanda", 10718379),
				new CountryStats(79, "Tunisia", 10673800), new CountryStats(80, "Portugal", 10561614),
				new CountryStats(81, "Czech Republic", 10507566), new CountryStats(82, "Bolivia", 10426155),
				new CountryStats(83, "Haiti", 10085214), new CountryStats(84, "Hungary", 9957731),
				new CountryStats(85, "Somalia", 9797000), new CountryStats(86, "Sweden", 9532634),
				new CountryStats(87, "Belarus", 9459800), new CountryStats(88, "Dominican Republic", 9445281),
				new CountryStats(89, "Benin", 9352000), new CountryStats(90, "Azerbaijan", 9235100),
				new CountryStats(91, "Burundi", 8749000), new CountryStats(92, "Austria", 8452835),
				new CountryStats(93, "Honduras", 8385072), new CountryStats(94, "United Arab Emirates", 8264070),
				new CountryStats(95, "South Sudan", 8260490), new CountryStats(96, "Switzerland", 8000001),
				new CountryStats(97, "Israel", 7941900), new CountryStats(98, "Tajikistan", 7800000),
				new CountryStats(99, "Bulgaria", 7364570), new CountryStats(100, "Papua New Guinea", 7170000),
				new CountryStats(101, "Serbia", 7120666), new CountryStats(102, "Hong Kong", 7103700),
				new CountryStats(103, "Libya", 6469000), new CountryStats(104, "Laos", 6465800),
				new CountryStats(105, "Paraguay", 6337127), new CountryStats(106, "Jordan", 6373000),
				new CountryStats(107, "Togo", 6191155), new CountryStats(108, "El Salvador", 6183000),
				new CountryStats(109, "Sierra Leone", 6126000), new CountryStats(110, "Nicaragua", 6071045),
				new CountryStats(111, "Denmark", 5587085), new CountryStats(112, "Eritrea", 5581000),
				new CountryStats(113, "Kyrgyzstan", 5477600), new CountryStats(114, "Slovakia", 5445324),
				new CountryStats(115, "Finland", 5425050), new CountryStats(116, "Singapore", 5312400),
				new CountryStats(117, "Turkmenistan", 5170000), new CountryStats(118, "Norway", 5049700),
				new CountryStats(119, "Ireland", 4588252), new CountryStats(120, "Central African Republic", 4576000),
				new CountryStats(121, "Georgia", 4497600), new CountryStats(122, "New Zealand", 4446530),
				new CountryStats(123, "Costa Rica", 4301712), new CountryStats(124, "Palestinian territories", 4293313),
				new CountryStats(125, "Lebanon", 4292000), new CountryStats(126, "Croatia", 4290612),
				new CountryStats(127, "Liberia", 4245000), new CountryStats(128, "Republic of the Congo", 4233000),
				new CountryStats(129, "Bosnia and Herzegovina", 3868621), new CountryStats(130, "Puerto Rico(USA)", 3706690),
				new CountryStats(131, "Kuwait", 3582054), new CountryStats(132, "Moldova", 3559500),
				new CountryStats(133, "Panama", 3405813), new CountryStats(134, "Mauritania", 3378254),
				new CountryStats(135, "Uruguay", 3286314), new CountryStats(136, "Armenia", 3275700),
				new CountryStats(137, "Lithuania", 3180394), new CountryStats(138, "Albania", 2831741),
				new CountryStats(139, "Oman", 2773479), new CountryStats(140, "Mongolia", 2736800),
				new CountryStats(141, "Jamaica", 2709300), new CountryStats(142, "Lesotho", 2217000),
				new CountryStats(143, "Namibia", 2104900), new CountryStats(144, "Latvia", 2070371),
				new CountryStats(145, "Macedonia", 2059794), new CountryStats(146, "Slovenia", 2061660),
				new CountryStats(147, "Botswana", 2024904), new CountryStats(148, "Gambia", 1825000),
				new CountryStats(149, "Qatar", 1757540), new CountryStats(150, "Gabon", 1564000),
				new CountryStats(151, "Guinea-Bissau", 1520830), new CountryStats(152, "Trinidad and Tobago", 1317714),
				new CountryStats(153, "Estonia", 1294236), new CountryStats(154, "Mauritius", 1286051),
				new CountryStats(155, "Bahrain", 1234571), new CountryStats(156, "Swaziland", 1220000),
				new CountryStats(157, "Timor-Leste", 1066409), new CountryStats(158, "Fiji", 876000),
				new CountryStats(159, "Cyprus", 838897), new CountryStats(160, "Djibouti", 818159),
				new CountryStats(161, "R‚union(France)", 816364), new CountryStats(162, "Guyana", 784894),
				new CountryStats(163, "Equatorial Guinea", 740000), new CountryStats(164, "Bhutan", 720679),
				new CountryStats(165, "Comoros", 669300), new CountryStats(166, "Montenegro", 620029),
				new CountryStats(167, "Macau(China)", 568700), new CountryStats(168, "Western Sahara", 567000),
				new CountryStats(169, "Solomon Islands", 553935), new CountryStats(170, "Suriname", 534000),
				new CountryStats(171, "Luxembourg", 511800), new CountryStats(172, "Cape Verde", 491875),
				new CountryStats(173, "Malta", 417617), new CountryStats(174, "Guadeloupe(France)", 401554),
				new CountryStats(175, "Martinique(France)", 396404), new CountryStats(176, "Brunei", 393162),
				new CountryStats(177, "Bahamas", 353658), new CountryStats(178, "Iceland", 320060),
				new CountryStats(179, "Maldives", 317280), new CountryStats(180, "Belize", 312971),
				new CountryStats(181, "French Polynesia(France)", 277000), new CountryStats(182, "Barbados", 274200),
				new CountryStats(183, "New Caledonia(France)", 245580), new CountryStats(184, "Vanuatu", 234023),
				new CountryStats(185, "French Guiana(France)", 224469), new CountryStats(186, "Mayotte(France)", 217000),
				new CountryStats(187, "Samoa", 186340), new CountryStats(188, "Sao Tom‚ and Principe", 172000),
				new CountryStats(189, "Saint Lucia", 166526), new CountryStats(190, "Guam(USA)", 159358),
				new CountryStats(191, "Cura‡ao(Netherlands)", 149679), new CountryStats(192, "Grenada", 110821),
				new CountryStats(193, "United States Virgin Islands", 106405), new CountryStats(194, "Tonga", 103036),
				new CountryStats(195, "Kiribati", 103000), new CountryStats(196, "Federated States of Micronesia", 102843),
				new CountryStats(197, "Aruba(Netherlands)", 101484),
				new CountryStats(198, "Saint Vincent and the Grenadines", 100892), new CountryStats(199, "Jersey(UK)", 97857),
				new CountryStats(200, "Seychelles", 90945), new CountryStats(201, "Antigua and Barbuda", 86295),
				new CountryStats(202, "Isle of Man(UK)", 84497), new CountryStats(203, "Andorra", 78115),
				new CountryStats(204, "Dominica", 71293), new CountryStats(205, "Bermuda(UK)", 64237),
				new CountryStats(206, "Guernsey(UK)", 62431), new CountryStats(207, "Greenland(Denmark)", 56749),
				new CountryStats(208, "American Samoa(USA)", 55519), new CountryStats(209, "Cayman Islands(UK)", 55456),
				new CountryStats(210, "Marshall Islands", 54305), new CountryStats(211, "Northern Mariana Islands(USA)", 53883),
				new CountryStats(212, "Saint Kitts and Nevis", 51970), new CountryStats(213, "Faroe Islands(Denmark)", 48459),
				new CountryStats(214, "Sint Maarten(Netherlands)", 37429), new CountryStats(215, "Saint Martin(France)", 36824),
				new CountryStats(216, "Liechtenstein", 36476), new CountryStats(217, "Monaco", 35000),
				new CountryStats(218, "San Marino", 32404), new CountryStats(219, "Turks and Caicos Islands(UK)", 31458),
				new CountryStats(220, "Gibraltar(UK)", 29752), new CountryStats(221, "British Virgin Islands(UK)", 29537),
				new CountryStats(222, "Land Islands(Finland)", 28355),
				new CountryStats(223, "Caribbean Netherlands(Netherlands)", 21133), new CountryStats(224, "Palau", 21000),
				new CountryStats(225, "Cook Islands(NZ)", 17791), new CountryStats(226, "Anguilla(UK)", 13452),
				new CountryStats(227, "Wallis and Futuna(France)", 13000), new CountryStats(228, "Nauru", 10000),
				new CountryStats(229, "Tuvalu", 10000), new CountryStats(230, "Saint Barth‚lemy(France)", 8902),
				new CountryStats(231, "Saint Pierre and Miquelon(France)", 6082), new CountryStats(232, "Montserrat(UK)", 4922),
				new CountryStats(233, "Saint Helena, Ascension and Tristan da Cunha(UK)", 4000),
				new CountryStats(234, "Falkland Islands(UK)", 2563),
				new CountryStats(235, "Svalbard and Jan Mayen(Norway)", 2495),
				new CountryStats(236, "Norfolk Island(Australia)", 2302),
				new CountryStats(237, "Christmas Island(Australia)", 2072), new CountryStats(238, "Tokelau(NZ)", 1411),
				new CountryStats(239, "Niue(NZ)", 1000), new CountryStats(240, "Vatican City", 800),
				new CountryStats(241, "Cocos (Keeling) Islands(Australia)", 550),
				new CountryStats(242, "Pitcairn Islands(UK)", 66) };
	}
}

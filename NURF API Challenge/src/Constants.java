import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Constants {

	public static List<Champion> champions = new ArrayList<Champion>(
		Arrays.asList(new Champion("Aatrox", 266)
		,new Champion("Ahri", 103)
		,new Champion("Akali", 84)
		,new Champion("Alistar", 12)
		,new Champion("Amumu", 32)
		,new Champion("Anivia", 34)
		,new Champion("Annie", 1)
		,new Champion("Ashe", 22)
		,new Champion("Azir", 268)
		,new Champion("Bard", 432)
		,new Champion("Blitzcrank", 53)
		,new Champion("Brand", 63)
		,new Champion("Braum", 201)
		,new Champion("Caitlyn", 51)
		,new Champion("Cassiopeia", 69)
		,new Champion("ChoGath", 31)
		,new Champion("Corki", 42)
		,new Champion("Darius", 122)
		,new Champion("Diana", 131)
		,new Champion("Draven", 119)
		,new Champion("DrMundo", 36)
		,new Champion("Elise", 60)
		,new Champion("Evelynn", 28)
		,new Champion("Ezreal", 81)
		,new Champion("FiddleSticks", 9)
		,new Champion("Fiora", 114)
		,new Champion("Fizz", 105)
		,new Champion("Galio", 3)
		,new Champion("Gangplank", 41)
		,new Champion("Garen", 86)
		,new Champion("Gnar", 150)
		,new Champion("Gragas", 79)
		,new Champion("Graves", 104)
		,new Champion("Hecarim", 120)
		,new Champion("Heimerdinger", 74)
		,new Champion("Irelia", 39)
		,new Champion("Janna", 40)
		,new Champion("JarvanIV", 59)
		,new Champion("Jax", 24)
		,new Champion("Jayce", 126)
		,new Champion("Jinx", 222)
		,new Champion("Kalista", 429)
		,new Champion("Karma", 43)
		,new Champion("Karthus", 30)
		,new Champion("Kassadin", 38)
		,new Champion("Katarina", 55)
		,new Champion("Kayle", 10)
		,new Champion("Kennen", 85)
		,new Champion("Khazix", 121)
		,new Champion("KogMaw", 96)
		,new Champion("Leblanc", 7)
		,new Champion("LeeSin", 64)
		,new Champion("Leona", 89)
		,new Champion("Lissandra", 127)
		,new Champion("Lucian", 236)
		,new Champion("Lulu", 117)
		,new Champion("Lux", 99)
		,new Champion("Malphite", 54)
		,new Champion("Malzahar", 90)
		,new Champion("Maokai", 57)
		,new Champion("MasterYi", 11)
		,new Champion("MissFortune", 21)
		,new Champion("MonkeyKing", 62)
		,new Champion("Mordekaiser", 82)
		,new Champion("Morgana", 25)
		,new Champion("Nami", 267)
		,new Champion("Nasus", 75)
		,new Champion("Nautilus", 111)
		,new Champion("Nidalee", 76)
		,new Champion("Nocturne", 56)
		,new Champion("Nunu", 20)
		,new Champion("Olaf", 2)
		,new Champion("Orianna", 61)
		,new Champion("Pantheon", 80)
		,new Champion("Poppy", 78)
		,new Champion("Quinn", 133)
		,new Champion("Rammus", 33)
		,new Champion("RekSai", 421)
		,new Champion("Renekton", 58)
		,new Champion("Rengar", 107)
		,new Champion("Riven", 92)
		,new Champion("Rumble", 68)
		,new Champion("Ryze", 13)
		,new Champion("Sejuani", 113)
		,new Champion("Shaco", 35)
		,new Champion("Shen", 98)
		,new Champion("Shyvana", 102)
		,new Champion("Singed", 27)
		,new Champion("Sion", 14)
		,new Champion("Sivir", 15)
		,new Champion("Skarner", 72)
		,new Champion("Sona", 37)
		,new Champion("Soraka", 16)
		,new Champion("Swain", 50)
		,new Champion("Syndra", 134)
		,new Champion("Talon", 91)
		,new Champion("Taric", 44)
		,new Champion("Teemo", 17)
		,new Champion("Thresh", 412)
		,new Champion("Tristana", 18)
		,new Champion("Trundle", 48)
		,new Champion("Tryndamere", 23)
		,new Champion("TwistedFate", 4)
		,new Champion("Twitch", 29)
		,new Champion("Udyr", 77)
		,new Champion("Urgot", 6)
		,new Champion("Varus", 110)
		,new Champion("Vayne", 67)
		,new Champion("Veigar", 45)
		,new Champion("Velkoz", 161)
		,new Champion("Vi", 254)
		,new Champion("Viktor", 112)
		,new Champion("Vladimir", 8)
		,new Champion("Volibear", 106)
		,new Champion("Warwick", 19)
		,new Champion("Xerath", 101)
		,new Champion("XinZhao", 5)
		,new Champion("Yasuo", 157)
		,new Champion("Yorick", 83)
		,new Champion("Zac", 154)
		,new Champion("Zed", 238)
		,new Champion("Ziggs", 115)
		,new Champion("Zilean", 26)
		,new Champion("Zyra", 143)));
	
	public static Map<Integer, Integer> keyToId;
	static {
		keyToId = new HashMap<Integer, Integer>();
		keyToId.put(266, 0);	
		keyToId.put(103,1);
		keyToId.put(84,2);
		keyToId.put(12,3);
		keyToId.put(32,4);
		keyToId.put(34,5);
		keyToId.put(1,6);
		keyToId.put(22,7);
		keyToId.put(268,8);
		keyToId.put(432,9);
		keyToId.put(53,10);
		keyToId.put(63,11);
		keyToId.put(201,12);
		keyToId.put(51,13);
		keyToId.put(69,14);
		keyToId.put(31,15);
		keyToId.put(42,16);
		keyToId.put(122,17);
		keyToId.put(131,18);
		keyToId.put(119,19);
		keyToId.put(36,20);
		keyToId.put(60,21);
		keyToId.put(28,22);
		keyToId.put(81,23);
		keyToId.put(9,24);
		keyToId.put(114,25);
		keyToId.put(105,26);
		keyToId.put(3,27);
		keyToId.put(41,28);
		keyToId.put(86,29);
		keyToId.put(150,30);
		keyToId.put(79,31);
		keyToId.put(104,32);
		keyToId.put(120,33);
		keyToId.put(74,34);
		keyToId.put(39,35);
		keyToId.put(40,36);
		keyToId.put(59,37);
		keyToId.put(24,38);
		keyToId.put(126,39);
		keyToId.put(222,40);
		keyToId.put(429,41);
		keyToId.put(43,42);
		keyToId.put(30,43);
		keyToId.put(38,44);
		keyToId.put(55,45);
		keyToId.put(10,46);
		keyToId.put(85,47);
		keyToId.put(121,48);
		keyToId.put(96,49);
		keyToId.put(7,50);
		keyToId.put(64,51);
		keyToId.put(89,52);
		keyToId.put(127,53);
		keyToId.put(236,54);
		keyToId.put(117,55);
		keyToId.put(99,56);
		keyToId.put(54,57);
		keyToId.put(90,58);
		keyToId.put(57,59);
		keyToId.put(11,60);
		keyToId.put(21,61);
		keyToId.put(62,62);
		keyToId.put(82,63);
		keyToId.put(25,64);
		keyToId.put(267,65);
		keyToId.put(75,66);
		keyToId.put(111,67);
		keyToId.put(76,68);
		keyToId.put(56,69);
		keyToId.put(20,70);
		keyToId.put(2,71);
		keyToId.put(61,72);
		keyToId.put(80,73);
		keyToId.put(78,74);
		keyToId.put(133,75);
		keyToId.put(33,76);
		keyToId.put(421,77);
		keyToId.put(58,78);
		keyToId.put(107,79);
		keyToId.put(92,80);
		keyToId.put(68,81);
		keyToId.put(13,82);
		keyToId.put(113,83);
		keyToId.put(35,84);
		keyToId.put(98,85);
		keyToId.put(102,86);
		keyToId.put(27,87);
		keyToId.put(14,88);
		keyToId.put(15,89);
		keyToId.put(72,90);
		keyToId.put(37,91);
		keyToId.put(16,92);
		keyToId.put(50,93);
		keyToId.put(134,94);
		keyToId.put(91,95);
		keyToId.put(44,96);
		keyToId.put(17,97);
		keyToId.put(412,98);
		keyToId.put(18,99);
		keyToId.put(48,100);
		keyToId.put(23,101);
		keyToId.put(4,102);
		keyToId.put(29,103);
		keyToId.put(77,104);
		keyToId.put(6,105);
		keyToId.put(110,106);
		keyToId.put(67,107);
		keyToId.put(45,108);
		keyToId.put(161,109);
		keyToId.put(254,110);
		keyToId.put(112,111);
		keyToId.put(8,112);
		keyToId.put(106,113);
		keyToId.put(19,114);
		keyToId.put(101,115);
		keyToId.put(5,116);
		keyToId.put(157,117);
		keyToId.put(83,118);
		keyToId.put(154,119);
		keyToId.put(238,120);
		keyToId.put(115,121);
		keyToId.put(26,122);
		keyToId.put(143,123);
	}
}

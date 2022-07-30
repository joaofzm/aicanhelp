package br.com.joaofzm15.aicanhelp.backEnd.entitites.enums;

public enum OppDeck {
	
	ALL_DECKS (0),
	Unknown(1),
	Zoodiac_tri_brigade(2),
	Drytron(3),
	Adamancipator(4),
	Virtual_world(5),
	Eldlich(6),
	Sky_striker(7),
	Swordsoul(8),
	EMPTY2(9),
	EMPTY3(10),
	EMPTY4(11),
	EMPTY5(12),
	EMPTY6(13),
	EMPTY7(14),
	EMPTY8(15),
	Numeron(16),
	DDD(17),
	Altergeist(18),
	Code_talker(19),
	Cyber_dragon(20),
	Dragonmaid(21),
	Dinos(22),
	Dragon_link(23),
	Endymion(24),
	Fluffal(25),
	Heros(26),
	Ignister(27),
	Invoked_dogmatika_shaddoll(28),
	Livetwin(29),
	Lyrilusc(30),
	Lyrilusc_tri_brigade(31),
	Phantom_knights(32),
	Prank_kids(33),
	Thunder_dragon(34),
	Traptrix(35),
	True_draco(36),
	Tri_brigade(37),
	FAKE_DECK(38),
	Despia(39);
	
	private int code;

	private OppDeck(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static OppDeck returnTheOppDeckThatCorespondsToTheParameterIntegerCode(int code) {
		for (OppDeck value : OppDeck.values()) {
			if (value.code == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OppDeck code");
	}

}

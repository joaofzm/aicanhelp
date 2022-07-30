package br.com.joaofzm15.aicanhelp.frontEnd.logic;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import br.com.joaofzm15.aicanhelp.backEnd.entitites.Duel;
import br.com.joaofzm15.aicanhelp.backEnd.entitites.enums.OppDeck;
import br.com.joaofzm15.aicanhelp.frontEnd.http.FrontEndInMemoryData;

public class DuelListFilter {
	
	private static final Instant minDateForCoinAndFirstSecond = Instant.parse("2022-03-10T00:00:00Z");

	public static List<Duel> filterOnlyFromSelectedSeason(List<Duel> list) {
		List<Duel> toBeReturned = new ArrayList<>();
		for (Duel duel : list) {
			if (duel.getDate().isAfter(getMinDateForCurrentSeason())
					&& duel.getDate().isBefore(getMaxDateForCurrentSeason())){
				toBeReturned.add(duel);
			}
		}
		return toBeReturned;
	}

	private static Instant getMinDateForCurrentSeason() {
		switch (FrontEndInMemoryData.filteredSeason) {
		case 0:
			return   Instant.parse ( "2022-01-18T00:00:00Z" );
		case 1:
			return   Instant.parse ( "2022-01-18T00:00:00Z" );
		case 2:
			return   Instant.parse ( "2022-02-01T01:00:00Z" );
		case 3:
			return   Instant.parse ( "2022-03-01T01:00:00Z" );
		case 4:
			return   Instant.parse ( "2022-04-01T01:00:00Z" );
		case 5:
			return   Instant.parse ( "2022-05-01T01:00:00Z" );
		case 6:
			return   Instant.parse ( "2022-06-01T01:00:00Z" );
		case 7:
			return   Instant.parse ( "2022-07-01T01:00:00Z" );
		case 8:
			return   Instant.parse ( "2022-08-01T01:00:00Z" );
		case 9:
			return   Instant.parse ( "2022-09-01T01:00:00Z" );
		case 10:
			return   Instant.parse ( "2022-10-01T01:00:00Z" );
		default:
			return null;
		}
	}

	private static Instant getMaxDateForCurrentSeason() {
		switch (FrontEndInMemoryData.filteredSeason) {
		case 0:
			return   Instant.parse ( "2070-01-31T23:00:00Z" );
		case 1:
			return   Instant.parse ( "2022-01-31T23:00:00Z" );
		case 2:
			return   Instant.parse ( "2022-02-28T23:00:00Z" );
		case 3:
			return   Instant.parse ( "2022-03-31T23:00:00Z" );
		case 4:
			return   Instant.parse ( "2022-04-30T23:00:00Z" );
		case 5:
			return   Instant.parse ( "2022-05-31T23:00:00Z" );
		case 6:
			return   Instant.parse ( "2022-06-30T23:00:00Z" );
		case 7:
			return   Instant.parse ( "2022-07-31T23:00:00Z" );
		case 8:
			return   Instant.parse ( "2022-08-31T23:00:00Z" );
		case 9:
			return   Instant.parse ( "2022-09-30T23:00:00Z" );
		case 10:
			return   Instant.parse ( "2022-10-31T23:00:00Z" );
		default:
			return null;
		}
	}

	public static List<Duel> filterOnlyAgainst(List<Duel> list, OppDeck oppDeck) {
		List<Duel> toBeReturned = new ArrayList<>();
		for (Duel duel : list) {
			if (duel.getOppDeck() == oppDeck) {
				toBeReturned.add(duel);
			}
		}
		return toBeReturned;
	}

	public static List<Duel> filterOnlyWins(List<Duel> list) {
		List<Duel> toBeReturned = new ArrayList<>();
		for (Duel duel : list) {
			if (duel.isResult()) {
				toBeReturned.add(duel);
			}
		}
		return toBeReturned;
	}

	public static List<Duel> filterOnlyLosses(List<Duel> list) {
		List<Duel> toBeReturned = new ArrayList<>();
		for (Duel duel : list) {
			if (!duel.isResult()) {
				toBeReturned.add(duel);
			}
		}
		return toBeReturned;
	}

	public static List<Duel> filterOnlyWonCoin(List<Duel> list) {
		List<Duel> toBeReturned = new ArrayList<>();
		for (Duel duel : list) {
			if (duel.isCoinResult() && duel.getDate().isAfter(minDateForCoinAndFirstSecond)) {
				toBeReturned.add(duel);
			}
		}
		return toBeReturned;
	}

	public static List<Duel> filterOnlyLostCoin(List<Duel> list) {
		List<Duel> toBeReturned = new ArrayList<>();
		for (Duel duel : list) {
			if (!duel.isCoinResult() && duel.getDate().isAfter(minDateForCoinAndFirstSecond)) {
				toBeReturned.add(duel);
			}
		}
		return toBeReturned;
	}

	public static List<Duel> filterOnlyWentFirst(List<Duel> list) {
		List<Duel> toBeReturned = new ArrayList<>();
		for (Duel duel : list) {
			if (duel.isFirst() && duel.getDate().isAfter(minDateForCoinAndFirstSecond)) {
				toBeReturned.add(duel);
			}
		}
		return toBeReturned;
	}

	public static List<Duel> filterOnlyWentSecond(List<Duel> list) {
		List<Duel> toBeReturned = new ArrayList<>();
		for (Duel duel : list) {
			if (!duel.isFirst() && duel.getDate().isAfter(minDateForCoinAndFirstSecond)) {
				toBeReturned.add(duel);
			}
		}
		return toBeReturned;
	}
}

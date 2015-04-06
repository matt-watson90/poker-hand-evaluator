package com.mattwatson.poker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluator
{

	public String evaluateHand(String hand)
	{
		
		if(hasFlush(hand) && hasStraight(hand)){
			return "Straight Flush";
		}
		if (hasfourOfAKind(hand))
		{
			return "Four of a Kind";
		}
		if (hasThreeOfAKind(hand) && hasPair(hand))
		{
			return "Full house";
		}
		if (hasFlush(hand))
		{
			return "Flush";
		}
		if (hasStraight(hand))
		{
			return "Straight";
		}
		if (hasThreeOfAKind(hand))
		{
			return "Three of a Kind";
		}
		if (hasTwoPair(hand))
		{
			return "Two Pair";
		}
		if (hasPair(hand))
		{
			return "Pair";
		} else
		{
			return "High Card";
		}
	}

	private boolean hasStraight(String hand)
	{
		String[] split = hand.split("\\s");
		List<Integer> intValuesOfCardsAceHigh = convertCardsToIntegerValues(split, 14);
		List<Integer> intValuesOfCardsAceLow = convertCardsToIntegerValues(split, 1);
		
		return sortCardValuesAndLookForStraight(intValuesOfCardsAceHigh) || sortCardValuesAndLookForStraight(intValuesOfCardsAceLow);
	}

	private List<Integer> convertCardsToIntegerValues(String[] split, Integer defaultAceValue)
	{
		List<Integer> intValuesOfCards = new ArrayList<Integer>();
		for (int i = 0; i < split.length; i++)
		{
			String value = split[i].substring(0, 1);
			switch (value)
			{
			case "A":
				intValuesOfCards.add(defaultAceValue);
				break;
			case "K":
				intValuesOfCards.add(13);
				break;
			case "Q":
				intValuesOfCards.add(12);
				break;
			case "J":
				intValuesOfCards.add(11);
				break;
			case "T":
				intValuesOfCards.add(10);
				break;
			default:
				intValuesOfCards.add(Integer.valueOf(value));
				break;
			}
		}
		return intValuesOfCards;
	}

	private boolean sortCardValuesAndLookForStraight(
			List<Integer> intValuesOfCards)
	{
		Collections.sort(intValuesOfCards);
		boolean inOrder = true;
		for (int i = 0; i < intValuesOfCards.size()-1; i++)
		{
			Integer currentValue = intValuesOfCards.get(i);
			Integer nextValue = intValuesOfCards.get(i+1);
			inOrder &= nextValue.equals(currentValue + 1);
		}
		return inOrder;
	}

	private boolean hasTwoPair(String hand)
	{
		String[] split = hand.split("\\s");
		Map<String, Integer> setOfValues = addValueOfCardsToMap(split);
		return setOfValues.size() == 3 && setOfValues.containsValue(2);

	}

	private boolean hasFlush(String hand)
	{
		String[] split = hand.split("\\s");
		String initalSuit = split[0].substring(1);
		boolean stillFlush = true;

		for (int i = 1; i < split.length; i++)
		{
			stillFlush &= split[1].substring(1).equals(initalSuit);
		}

		return stillFlush;
	}

	private boolean hasfourOfAKind(String hand)
	{
		String[] split = hand.split("\\s");
		Map<String, Integer> setOfValues = addValueOfCardsToMap(split);
		return setOfValues.containsValue(4);
	}

	private boolean hasThreeOfAKind(String hand)
	{
		String[] split = hand.split("\\s");
		Map<String, Integer> setOfValues = addValueOfCardsToMap(split);
		return setOfValues.containsValue(3);
	}

	private Boolean hasPair(String hand)
	{
		String[] split = hand.split("\\s");
		Map<String, Integer> setOfValues = addValueOfCardsToMap(split);
		return setOfValues.containsValue(2);
	}

	private Map<String, Integer> addValueOfCardsToMap(String[] split)
	{
		Map<String, Integer> allValues = new HashMap<String, Integer>();
		for (String string : split)
		{
			String valueOfCard = string.substring(0, 1);
			if (!allValues.containsKey(valueOfCard))
			{
				allValues.put(valueOfCard, 1);
			} else
			{
				Integer currentValue = allValues.get(valueOfCard);
				allValues.put(valueOfCard, currentValue + 1);
			}
		}
		return allValues;
	}
}

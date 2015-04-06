package com.mattwatson.test;
import static org.junit.Assert.*;

import org.junit.Test;

import com.mattwatson.poker.Evaluator;

public class EvaluatorTest
{

	@Test
	public void highCard()
	{
		Evaluator evaluator = new Evaluator();
		String evaluateHand = evaluator.evaluateHand("5D 2H KS JC 3H");

		assertEquals("High Card", evaluateHand);
	}

	@Test
	public void pairs()
	{
		Evaluator evaluator = new Evaluator();
		String evaluateHand = evaluator.evaluateHand("5D 5H KS JC 3H");

		assertEquals("Pair with number cards not found", "Pair", evaluateHand);

		evaluateHand = evaluator.evaluateHand("3D 5H KS JC 5H");

		assertEquals("Pair with face cards not found", "Pair", evaluateHand);

	}

	@Test
	public void threeOfAKind()
	{
		Evaluator evaluator = new Evaluator();
		String evaluateHand = evaluator.evaluateHand("5D 5H 5S JC 3H");

		assertEquals("Three of a kind with number cards not found",
				"Three of a Kind", evaluateHand);

		evaluateHand = evaluator.evaluateHand("KD 2H KS JC KH");

		assertEquals("Three of a kind with face cards not found",
				"Three of a Kind", evaluateHand);

	}

	@Test
	public void fourOfAKind()
	{
		Evaluator evaluator = new Evaluator();
		String evaluateHand = evaluator.evaluateHand("5D 5H 5S JC 5H");

		assertEquals("Four of a kind with number cards not found",
				"Four of a Kind", evaluateHand);

		evaluateHand = evaluator.evaluateHand("KD 2H KS KC KH");

		assertEquals("Four of a kind with face cards not found",
				"Four of a Kind", evaluateHand);
	}

	@Test
	public void fullHouse()
	{
		Evaluator evaluator = new Evaluator();
		String evaluateHand = evaluator.evaluateHand("5D 5H 5S JC JH");

		assertEquals("Full house with fives and jacks not found", "Full house",
				evaluateHand);

		evaluateHand = evaluator.evaluateHand("TD TH JS JC TH");

		assertEquals("Full house with tens and jacks not found", "Full house",
				evaluateHand);
	}

	@Test
	public void flush()
	{
		Evaluator evaluator = new Evaluator();
		String evaluateHand = evaluator.evaluateHand("4H 5H TH AH 3H");

		assertEquals("Flush not found", "Flush", evaluateHand);
	}

	@Test
	public void twoPairs()
	{
		Evaluator evaluator = new Evaluator();
		String evaluateHand = evaluator.evaluateHand("2S 4D TS 4H TH");

		assertEquals("Two pair not found", "Two Pair", evaluateHand);
	}

	@Test
	public void straight()
	{
		Evaluator evaluator = new Evaluator();

		String evaluateHand = evaluator.evaluateHand("3S 5D 4S 7H 6H");
		assertEquals("Straight not found", "Straight", evaluateHand);

		evaluateHand = evaluator.evaluateHand("AS 3D 4S 2H 5H");
		assertEquals("Ace low straight not found", "Straight", evaluateHand);

		evaluateHand = evaluator.evaluateHand("JS TD KS QH AH");
		assertEquals("Ace high straight not found", "Straight", evaluateHand);
	}

	@Test
	public void straightFlush()
	{
		Evaluator evaluator = new Evaluator();

		String evaluateHand = evaluator.evaluateHand("3D 5D 4D 7D 6D");
		assertEquals("Straight flush not found", "Straight Flush", evaluateHand);
		
		evaluateHand = evaluator.evaluateHand("AH 3H 4H 2H 5H");
		assertEquals("Ace low straight flush not found", "Straight Flush", evaluateHand);

		evaluateHand = evaluator.evaluateHand("JS TS KS QS AS");
		assertEquals("Royal flush not found", "Straight Flush", evaluateHand);
	}

}

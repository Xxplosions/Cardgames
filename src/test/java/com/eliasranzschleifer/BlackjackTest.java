package com.eliasranzschleifer;

import org.junit.Test;

import static com.eliasranzschleifer.BlackJack.winningHand;
import static com.eliasranzschleifer.BlackJack.winningHand;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BlackjackTest {
    @Test
    public void scoreTest() {
        Card card = new Card(3, Card.Suit.HEARTS, Card.Value.THREE);
        Card secretCard = new Card(10, Card.Suit.HEARTS, Card.Value.TEN);
        BlackjackHand hand = new BlackjackHand(secretCard,card);



        assertThat("Public score is 3",hand.score(false), is(3));
        assertThat("Score is 13",hand.score(true), is(13));
    }

    @Test
    public void playerWinsWhenScoreIsHigherAndNotBust() {
        Card playerCard = new Card(5, Card.Suit.HEARTS, Card.Value.FIVE);
        Card playerSecretCard = new Card(9, Card.Suit.HEARTS, Card.Value.NINE);
        BlackjackHand playerHand = new BlackjackHand(playerSecretCard,playerCard);

        Card dealerCard = new Card(3, Card.Suit.HEARTS, Card.Value.THREE);
        Card dealerSecretCard = new Card(10, Card.Suit.HEARTS, Card.Value.TEN);
        BlackjackHand dealerHand = new BlackjackHand(dealerSecretCard,dealerCard);

        assertThat("Player Wins",winningHand(playerHand,dealerHand),is(playerHand));
    }
    @Test
    public void playerWinsWhenScoreIsLowerAndDealearIsBust() {
        Card playerCard = new Card(5, Card.Suit.HEARTS, Card.Value.FIVE);
        Card playerSecretCard = new Card(9, Card.Suit.HEARTS, Card.Value.NINE);
        BlackjackHand playerHand = new BlackjackHand(playerSecretCard,playerCard);

        Card dealerCard = new Card(3, Card.Suit.HEARTS, Card.Value.THREE);
        Card dealerSecretCard = new Card(10, Card.Suit.HEARTS, Card.Value.TEN);
        BlackjackHand dealerHand = new BlackjackHand(dealerSecretCard,dealerCard);
        Card dealerPublicCard = new Card(10, Card.Suit.CLUBS,Card.Value.TEN);
        dealerHand.dealCard(dealerPublicCard);

        assertThat("Player Wins",winningHand(playerHand,dealerHand),is(playerHand));
    }

    @Test
    public void dealerWinsWhenScoreIsTied() {
        Card playerCard = new Card(5,Card.Suit.CLUBS, Card.Value.FIVE);
        Card playerSecretCard = new Card(6,Card.Suit.CLUBS,Card.Value.SIX);
        BlackjackHand playerHand = new BlackjackHand(playerSecretCard,playerCard);

        Card dealerCard = new Card(5,Card.Suit.HEARTS, Card.Value.FIVE);
        Card dealerSecretCard = new Card(6,Card.Suit.HEARTS,Card.Value.SIX);
        BlackjackHand dealerHand = new BlackjackHand(dealerSecretCard,dealerCard);

        assertThat("Dealer Wins",winningHand(playerHand,dealerHand),is(dealerHand));
    }

    @Test
    public void dealerWinsWhenScoreIsLowerAndPlayerIsBust() {
        Card playerCard = new Card(7,Card.Suit.CLUBS, Card.Value.SEVEN);
        Card playerSecretCard = new Card(10,Card.Suit.CLUBS,Card.Value.TEN);
        BlackjackHand playerHand = new BlackjackHand(playerSecretCard,playerCard);
        Card playerPublicCard = new Card(10, Card.Suit.DIAMONDS,Card.Value.TEN);
        playerHand.dealCard(playerPublicCard);

        Card dealerCard = new Card(5,Card.Suit.HEARTS, Card.Value.FIVE);
        Card dealerSecretCard = new Card(6,Card.Suit.HEARTS,Card.Value.SIX);
        BlackjackHand dealerHand = new BlackjackHand(dealerSecretCard,dealerCard);

        assertThat("Dealer Wins",winningHand(playerHand,dealerHand),is(dealerHand));
    }

    @Test
    public void dealerWinsWhenBothAreBust() {
        Card playerCard = new Card(2,Card.Suit.CLUBS, Card.Value.TWO);
        Card playerSecretCard = new Card(10,Card.Suit.CLUBS,Card.Value.TEN);
        BlackjackHand playerHand = new BlackjackHand(playerSecretCard,playerCard);
        Card playerPublicCard = new Card(10, Card.Suit.DIAMONDS,Card.Value.TEN);
        playerHand.dealCard(playerPublicCard);

        Card dealerCard = new Card(3,Card.Suit.HEARTS, Card.Value.THREE);
        Card dealerSecretCard = new Card(10,Card.Suit.HEARTS,Card.Value.TEN);
        BlackjackHand dealerHand = new BlackjackHand(dealerSecretCard,dealerCard);
        Card dealerPublicCard = new Card(10, Card.Suit.SPADES,Card.Value.TEN);
        dealerHand.dealCard(dealerPublicCard);

        assertThat("Dealer Wins",winningHand(playerHand,dealerHand),is(dealerHand));

        playerHand.dealCard(new Card(2,Card.Suit.SPADES,Card.Value.TWO));
        dealerHand.dealCard(new Card(5,Card.Suit.SPADES,Card.Value.FIVE));
        assertThat("Dealer Wins",winningHand(playerHand,dealerHand),is(dealerHand));

    }

    @Test
    public void dealerWinsWhenScoreIsHigherAndNotBust() {
        Card playerCard = new Card(3, Card.Suit.HEARTS, Card.Value.THREE);
        Card playerSecretCard = new Card(10, Card.Suit.HEARTS, Card.Value.TEN);
        BlackjackHand playerHand = new BlackjackHand(playerSecretCard,playerCard);

        Card dealerCard = new Card(5, Card.Suit.HEARTS, Card.Value.FIVE);
        Card dealerSecretCard = new Card(9, Card.Suit.HEARTS, Card.Value.NINE);
        BlackjackHand dealerHand = new BlackjackHand(dealerSecretCard,dealerCard);

        assertThat("Dealer Wins",winningHand(playerHand,dealerHand),is(dealerHand));

    }

    @Test
    public void aceScoringTest() {
        Card playerCard = new Card(1,Card.Suit.CLUBS, Card.Value.ACE);
        Card playerSecretCard = new Card(1,Card.Suit.HEARTS, Card.Value.ACE);
        BlackjackHand playerHand = new BlackjackHand(playerSecretCard,playerCard);
        Card playerPublicCard = new Card(3,Card.Suit.CLUBS,Card.Value.THREE);
        playerHand.dealCard(playerPublicCard);

        Card dealerCard = new Card(3,Card.Suit.HEARTS, Card.Value.THREE);
        Card dealerSecretCard = new Card(10,Card.Suit.HEARTS,Card.Value.TEN);
        BlackjackHand dealerHand = new BlackjackHand(dealerSecretCard,dealerCard);

        assertThat("Player Wins",winningHand(playerHand,dealerHand),is(playerHand));
    }


}

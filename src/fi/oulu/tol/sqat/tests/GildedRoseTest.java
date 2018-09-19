package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
    
	@Test
	public void testUpdateEndOfDay_Sulfuras_Quality_80() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros",0, 80));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemSulfuras = items.get(0);
		assertEquals("Sulfuras quality should be always 80!", 80, itemSulfuras.getQuality());				
	}

	@Test
	public void testUpdateEndOfDay_Elixir_of_the_Mongoose() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Elixir of the Mongoose", 5, 7));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemElixir = items.get(0);
		assertEquals("Quality of Elixir of the Mongoose", 6, itemElixir.getQuality());				
	}
}

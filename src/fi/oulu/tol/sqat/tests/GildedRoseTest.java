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
		assertEquals(1, itemBrie.getSellIn());
	}
   
	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_3_days() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals("Should be 13, no?",13, itemBrie.getQuality());
		assertEquals("Should be 0, can't get below 0, yes?", 0, itemBrie.getSellIn());
	}	
	
	@Test
	public void testUpdateEndOfDay_Sulfuras_Quality_80() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros",0, 80));
		
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemSulfuras = items.get(0);
		assertEquals("Sulfuras quality should be always 80!", 80, itemSulfuras.getQuality());
		assertEquals("Sulfuras sell in value should be 0", 0, itemSulfuras.getSellIn());
	}

	@Test
	public void testUpdateEndOfDay_Elixir_of_the_Mongoose_1_day() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Elixir of the Mongoose", 5, 7));
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemElixir = items.get(0);
		assertEquals("Quality of Elixir of the Mongoose", 6, itemElixir.getQuality());
		assertEquals("SellIn", 4, itemElixir.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_Elixir_of_the_Mongoose_3_days() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Elixir of the Mongoose", 5, 7));
		
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemElixir = items.get(0);
		assertEquals("Quality of Elixir of the Mongoose", 4, itemElixir.getQuality());
		assertEquals("SellIn", 2, itemElixir.getSellIn());
	}	

	@Test
	public void testUpdateEndOfDay_List_tests() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Elixir of the Mongoose", 5, 7));
		store.addItem(new Item("Sulfuras, Hand of Ragnaros",0, 80));
		store.addItem(new Item("Aged Brie", 2, 10) );

		List<Item> items = store.getItems();
		Item Mongoose = items.get(0);
		String monttu = Mongoose.getName();
		Item Sulfura = items.get(1);
		String sulf = Sulfura.getName();
		Item Brie = items.get(2);
		String abrie = Brie.getName();
		
		assertEquals("List size should be 3", 3, items.size());
		assertEquals("List(0)", "Elixir of the Mongoose", monttu);
		assertEquals("List(1)", "Sulfuras, Hand of Ragnaros", sulf);
		assertEquals("List(2)", "Aged Brie", abrie);
	}	
}

import java.util.List;
import java.util.List;

//TODO: complete this object/class

public class PaginationHelper<I> {

	private static int Col;
	private static int items;

	/**
	 * The constructor takes in an array of items and a integer indicating how many
	 * items fit within a single page
	 */
	public PaginationHelper(List<I> collection, int itemsPerPage) {
		this.Col = collection.size();
		items = itemsPerPage;
	}

	/**
	 * returns the number of items within the entire collection
	 */
	public int itemCount() {
		return items;

	}

	/**
	 * returns the number of pages
	 */
	public int pageCount() {
		return Col / items;
	}

	/**
	 * returns the number of items on the current page. page_index is zero based.
	 * this method should return -1 for pageIndex values that are out of range
	 */
	public int pageItemCount(int pageIndex) {
		int ans = -1;
		if (pageIndex >= 0 && pageIndex <= Col) {
			if (pageIndex == pageCount() - 1)
				ans = pageIndex / items;
			else
				ans = items;
		}
		return ans;
	}

	public static String camelCase(String str) {
		String[] v = str.split(" ");
		String ans ="";
		for (String a : v) {
			a = Character.toUpperCase(a.charAt(0)) + a.substring(1,a.length()-1);
			ans.concat(a);
		}
		return ans;
	}

	/**
	 * determines what page an item is on. Zero based indexes this method should
	 * return -1 for itemIndex values that are out of range
	 */
	public int pageIndex(int itemIndex) {
		int page = -1;
		if (itemIndex >= 0 && itemIndex < Col) {
			page = (itemIndex - 1) / items;
		}
		return page;
	}
}
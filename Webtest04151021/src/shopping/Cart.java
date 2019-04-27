package shopping;

import java.util.ArrayList;


public class Cart {
	private ArrayList<Book> cartList;
	
	public Cart(){
		cartList = new ArrayList<Book>();
	}

	public ArrayList<Book> getCartList() {
		return cartList;
	}

	public void setCartList(ArrayList<Book> cartList) {
		this.cartList = cartList;
	}
	
	public void addBook(Book book)
	{
		int i = 0;
		if(cartList.size() != 0)
		{
			for(Book b : cartList)
			{
				++i;
				if(book.getId() == b.getId())
				{
					b.setNum(b.getNum() + 1);
					break;
				}
				else if(i == cartList.size())
				{
					book.setNum(1);
					cartList.add(book);
					break;
				}
				
			}
		}
		else
		{
			book.setNum(1);
			cartList.add(book);
		}
	}
	
	public void delBook(Book book)
	{
		int i = 0;
		if(cartList.size() != 0)
		{
			for(Book b : cartList)
			{
				++i;
				if(book.getId() == b.getId())
				{
					b.setNum(b.getNum() - 1);
					if(b.getNum() <= 0)
					{
						cartList.remove(b);
					}
					break;
				}
			}
		}
	}
}

package com.noobs.model;

import java.util.ArrayList;

public class demo
{
	public static void main(String[] args)
	{
		SQLite sqlite = new SQLite();

		ArrayList<MonHoc> list = sqlite.read();
		System.out.println(list.get(0).getTenMonHoc());

	}
}
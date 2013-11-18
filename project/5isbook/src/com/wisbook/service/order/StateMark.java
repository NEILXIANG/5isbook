package com.wisbook.service.order;

public enum StateMark {
	EQUALS{
		@Override
		public String toString() {
			return "=";
		}
	},
	NOTEQUALS{
		@Override
		public String toString() {
			return "!=";
		}
	}
}

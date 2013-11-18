package com.wisbook.model.order;

public enum OrderStateEnum {
	COMMITTED{
		@Override
		public String toString() {
			return "已提交";
		}
	},DISPOSED{
		@Override
		public String toString() {
			return "已处理";
		}
	},OUT_STORAGE{
		@Override
		public String toString() {
			return "已出库";
		}
	},RECEIVED{
		@Override
		public String toString() {
			return "已收货";
		}
	},CANCEL{
		@Override
		public String toString() {
			return "已取消";
		}
	}
}

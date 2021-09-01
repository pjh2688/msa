Ext.define("Study.store.OrderList", {
	extend : 'Ext.data.BufferedStore',
	alias : 'store.orderList',
	storeId : 'orderList',
	autoLoad : false,
	fields : ['id', 'member', 'orderProducts', 'delivery', 'orderDate', 'status', 'totalPrice'],
	proxy : {
		type : 'ajax',
		actionMethods : {
			read : 'GET'
		},
		url : '/orders',
		reader : {
			type : 'json',
			rootProperty : 'data',
			totalProperty : 'totalCount'
		}
	}
});
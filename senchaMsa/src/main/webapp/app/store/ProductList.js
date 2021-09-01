Ext.define("Study.store.ProductList", {
	extend : 'Ext.data.BufferedStore',
	alias : 'store.productList',
	storeId : 'productList',
	autoLoad : false,
	fields : ['product_id', 'name', 'price', 'stock'],
	proxy : {
		type : 'ajax',
		actionMethods : {
			read : 'GET'
		},
		url : '/products',
		reader : {
			type : 'json',
			rootProperty : 'data',
			totalProperty : 'totalCount'
		}
	}
});
Ext.define('Study.view.product.ProductListViewModel', {
    extend : 'Ext.app.ViewModel',
    alias : 'viewmodel.productList',
    data : {
    	id : 0,
    	name : '',
    	price : 0,
    	stock : 0,
    	searchValue : ''
    },
    stores : {
    	productList : {
    		type : 'productList'
    	}
    }
    
});
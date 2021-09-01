Ext.define('Study.view.order.OrderListViewModel', {
    extend : 'Ext.app.ViewModel',
    alias : 'viewmodel.orderList',
    data : {
    	id : 0,
    	member : '{member}',
    	orderProducts : '{orderProducts}',
    	delivery : '{delivery}',
    	orderDate : '',
    	status : '',
    	totalPrice : 0
    },
    stores : {
    	orderList : {
    		type : 'orderList'
    	}
    }
    
});
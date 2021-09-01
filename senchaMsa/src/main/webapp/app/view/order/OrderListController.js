Ext.define('Study.view.order.OrderListController', {
    extend : 'Ext.app.ViewController',
    alias : 'controller.orderList',
   
    onLoadData : function(obj) {
    	var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	var store = viewModel.getStore(view['xtype']);
    	
    	store.load();
    },
    searchBtn : function(btn) {
    	var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	var store = viewModel.getStore(view['xtype']);
    	
    	var searchValue = view.down("[name=searchValue]").getValue();
    	
    	store.getProxy().setExtraParam("searchValue", viewModel.get("searchValue"));
    	
    	store.load({
    		callback : function(data) {
    			viewModel.set("searchValue", "");
    			console.log(data);
    		}
    	});
    },
    
    deliveryInfoBtn : function(btn) {
    	var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	var store = viewModel.getStore(view['xtype']);
    	
    	var record = btn.getWidgetRecord();
    	
    	var member = record.get("member");
    	var delivery = record.get("delivery");
    	var orderProducts = record.get("orderProducts");
    	
    	// 2021-08-31 -> 여기까지 함
    	
    	console.log(record);
    	
    	console.log(member.name);
    	console.log(orderProducts);
    	for(var i = 0; i < orderProducts.length; i++) {
    		console.log(orderProducts[i].product.name);
    	}
    	console.log(orderProducts[0].product.name);
    	console.log(delivery.address);
    	console.log(delivery.zipcode);
    	console.log(delivery.status);
    	console.log(orderProducts.length);
    
		Ext.widget("selectDelivery", {
			deliveryNm : member.name,
			deliveryAddr : delivery.address,
			deliveryZipcode : delivery.zipcode,
			deliveryStatus : delivery.status

		});
		
		
    }
    
});
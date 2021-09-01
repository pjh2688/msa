Ext.define('Study.view.window.SelectDeliveryController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.selectDelivery',
    onLoadData : function(obj) {
    	var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	
    	viewModel.set("deliveryNm", view['deliveryNm']);
    	viewModel.set("deliveryAddress", "(" + view['deliveryZipcode'] + ") " + view['deliveryAddr']);
    	
    	var deliveryStatus = "";
    	
    	if(view['deliveryStatus'] == "READY") {
    		deliveryStatus = "배송준비중";
    	} else {
    		deliveryStatus = "배송완료";
    	}
    	
    	viewModel.set("deliveryStatus", deliveryStatus);
    	
    	console.log(view['deliveryNm']);
    	console.log(view['deliveryAddr']);
    	console.log(view['deliveryZipcode']);
    	console.log(view['deliveryStatus']);
    }
});
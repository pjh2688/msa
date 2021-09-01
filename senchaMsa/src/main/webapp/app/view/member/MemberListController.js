Ext.define('Study.view.member.MemberListController', {
    extend : 'Ext.app.ViewController',
    alias : 'controller.memberList',
    loginBtn : function(btn) {
    	console.log("로그인 클릭");
    	btn.up("window").close();
		Ext.widget("main");
    },
    
    onLoadData : function(obj) {
    	var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	var store = viewModel.getStore(view['xtype']);
    	
    	console.log(viewModel.get("id"));
    	console.log(viewModel.get("name"));
    	console.log(viewModel.get("password"));
    	console.log(viewModel.get("address"));
    	console.log(viewModel.get("zipcode"));
    	console.log(viewModel.get("phone"));
    
    	store.load();
    },
    
    searchBtn : function(btn) {
    	var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	var store = viewModel.getStore(view['xtype']);
    	
    	store.getProxy().setExtraParam("searchValue", viewModel.get("searchValue"));
    	
    	store.load({
    		callback : function(data) {
    			viewModel.set("searchValue", "");
    			console.log(data);
    		}
    	});
    	
    }
});
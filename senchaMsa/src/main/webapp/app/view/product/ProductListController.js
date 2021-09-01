Ext.define('Study.view.product.ProductListController', {
    extend : 'Ext.app.ViewController',
    alias : 'controller.productList',
    loginBtn : function(btn) {
    	console.log("로그인 클릭");
    	btn.up("window").close();
		Ext.widget("main");
    },
    /**
     *  1. 상품 목록 그리드 리사이즈 메소드
     */
    setGridHeight : function(obj) {
    	obj.down("grid").setHeight(Ext.Element.getViewportHeight()-150);
    },
    /**
     *  2. 그리드에 최초 데이터 로드 메소드
     */
    onLoadData : function(obj) {
    	var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	var store = viewModel.getStore(view['xtype']);
    	
    	store.load();
    },
    /**
     *  3-1. 상품 정보 저장/수정 메소드
     */
    addProduct : function(obj) {
    	var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	var url = '/products';
    	var method = 'POST';
    	
    	console.log(viewModel.get("name"));
    	console.log(viewModel.get("price"));
    	console.log(viewModel.get("stock"));
    	
    	var data = {
    		name : viewModel.get("name"),
    		price : viewModel.get("price"),
    		stock : viewModel.get("stock")
    	};
    	
    	/**
    	 * 3-3. UPDATE 
    	 */
    	if(viewModel.get("id") > 0) {
    		data.id = viewModel.get("id");
    		url = "/products/" + data.id;
    		method = 'PUT';	
    	}
    	
    	/**
    	 * 3-2. CREATE
    	 */
    	Ext.Ajax.request({
    		url : url,
    		headers: {
    			'Content-Type' : 'application/json' 
    		},
    		method : method,
    		params : JSON.stringify(data),
    		success : function(response) {
    			var result = Ext.decode(response.responseText);
    			
    			// 4. 정상등록일시
    			if(result['code'] == 200) {
    				// 5. 화면 reload 
    				viewModel.set("id", 0);
    		    	viewModel.set("name", "");
    		    	viewModel.set("price", 0);
    		    	viewModel.set("stock", 0);
    				viewModel.getStore(view['xtype']).reload();
    			} else {
    				console.log("등록실패");
    				return;
    			}
    		}
    	});
    },
    
    /**
     *  4. 상품 등록 취소 메소드
     */
	cancelProduct : function(btn) {
		var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	var record = btn.getWidgetRecord();
    	var id = record.get("id");
    	
    	if(id == 0) {
    		Ext.Msg.alert("알림", "아이템을 선택해주세요.");
    		return;
    	}
    	// 2021-08-16 -> 20:27초
    	Ext.Ajax.request({
    		url : /products/ + id,
    		method : 'DELETE',
    		success : function(response) {
    			var result = Ext.decode(response.responseText);
    			
    			// 4. 정상등록일시
    			if(result['code'] == 200) {
    				// 5. 화면 reload 
    				viewModel.set("id", 0);
    		    	viewModel.set("name", "");
    		    	viewModel.set("price", 0);
    		    	viewModel.set("stock", 0);
    				viewModel.getStore(view['xtype']).reload();
    			} else {
    				console.log("삭제 실패");
    				return;
    			}
    		}
    	});
    },
    
    /**
     *  5. 상품 검색 메소드
     */
    searchBtn : function(btn) {
    	var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	var store = viewModel.getStore(view['xtype']);
    	
    	store.getProxy().setExtraParam("searchValue", viewModel.get("searchValue"));
    	
    	store.load({
    		callback : function(data) {
    			viewModel.set("id", 0);
		    	viewModel.set("name", "");
		    	viewModel.set("price", 0);
		    	viewModel.set("stock", 0);
		    	viewModel.set("searchValue", "");
    			console.log(data);
    		}
    	});
    },
    
    /**
     *  6. 상품 정보 수정을 위한 메소드
     */
    onUpdateForm : function(obj, td, cellIndex, record, tr, rowIndex, e, eOpt) {
    	var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	
    	viewModel.set("id", record.get("id"));
    	viewModel.set("name", record.get("name"));
    	viewModel.set("price", record.get("price"));
    	viewModel.set("stock", record.get("stock"));
    	
    	console.log(record.get("id"));
    	console.log(record.get("name"));
    	console.log(record.get("price"));
    	console.log(record.get("stock"));
    },
    
    /**
     *  7. 초기화 버튼
     */
    formatBtn : function(btn) {
    	var me = this;
    	var view = me.getView();
    	var viewModel = me.getViewModel();
    	
    	viewModel.set("id", 0);
    	viewModel.set("name", "");
    	viewModel.set("price", 0);
    	viewModel.set("stock", 0);
    	
    }
});
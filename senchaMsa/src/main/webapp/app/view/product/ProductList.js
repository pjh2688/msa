Ext.define('Study.view.product.ProductList', {
	extend : 'Ext.form.Panel',
	xtype : 'productList',
    controller : 'productList',
    viewModel : 'productList',
    listeners : {
    	resize : 'setGridHeight',  // 1. 상품 목록 그리드 리사이즈 메소드
    	boxready : 'onLoadData' // 2. 그리드에 최초 데이터 로드 메소드
    },
	title : '상품 목록 ',
	margin : 10,
	items : [
		{
			xtype : 'toolbar',
			items : [
				{
					xtype : 'textfield',
					fieldLabel : '상품명',
					name : 'name',
					bind : {
						value : '{name}'
					}
				},
				{
					xtype : 'numberfield',
					fieldLabel : '가격',
					name : 'price',
					bind : {
						value : '{price}'
					}
				},
				{
					xtype : 'numberfield',
					fieldLabel : '재고량',
					name : 'stock',
					bind : {
						value : '{stock}'
					}
				},
				{
					xtype : 'button',
					text : '등록',
					handler : 'addProduct'
				},
				{
					xtype : 'button',
					text : '초기화',
					handler : 'formatBtn'
				}
				
			]
		},
		{
			xtype : 'grid',
			listeners : {
				celldblclick : 'onUpdateForm'
			},
			height : 150,
			viewConfig : {
				emptyText : '상품이 존재하지 않습니다.'
			},
			columnLines : true,
			tbar : [
				{
					xtype : 'textfield',
					name : 'searchValue',
					emptyText : '검색어를 입력하세요.',
					flex: 1,
					bind : {
						value : '{searchValue}'
					}
				},
				{
					xtype : 'button',
					text : '검색',
					handler : 'searchBtn'
				}
			],
			columns : [
				{
					xtype : 'rownumberer'
				},
				{
					text : '상품명',
					flex : 1,
					dataIndex : 'name'
				},
				{
					text : '가격',
					flex : 1,
					dataIndex : 'price'
				},
				{
					text : '재고량',
					flex : 1,
					dataIndex : 'stock'
				},
				{
					xtype : 'widgetcolumn',
					widget : {
						xtype : 'button',
						text : '주문 취소',
						handler : 'cancelProduct'
					}
				}
			],
			bind : {
				store : '{productList}'
			}
		}
	]

});
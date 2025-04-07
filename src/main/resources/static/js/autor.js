const app = Vue.createApp({
	created() {
		this.carregaDominio();
	},
	computed() { },
	mounted() { window.$app = this; },
	watch: { },
	data() {
		return {
			count: 0,
			config: {
				table: {
					loading: false,
					pagination: {
						page: 1,
						size: 10,
						sortBy: [],
						search: '',
						totalItems: 0,
					},
				},
				dialogItem: {
					abrir: false,
					editar: false,
				},
			},
			listasInternas: {},
			headers: [
				{ title: 'ID', key: 'id',align: 'left', sortable: true, },
				{ title: 'Nome', key: 'nome', align: 'left', sortable: true, },
			],
			api: {
				principal: "../api/autor",
			},
			consulta: {
				filtro: {},
				resultado: [],
			},
			itemSelecionado: {},
		}
	},
	methods: {
		carregaDominio() { },
		limpar() {
			this.$refs.formFiltro.reset();
			this.consulta.resultado = [];
			this.limparItemSelecionado();
		},
		limparItemSelecionado() {
			this.$refs.formItem.reset();
			this.itemSelecionado = {};
		},
		gerar() { 
			this.limparItemSelecionado();
			this.config.dialogItem.editar=false;
			this.config.dialogItem.abrir=true; 
		},
		editar(item) {
			this.limparItemSelecionado();
			this.config.dialogItem.editar=true;
			this.itemSelecionado = item;
			this.config.dialogItem.abrir=true; 
		},
		deletar(item) {},
		salvar() {
			if (this.$refs.formItem.validate()){
				if (this.config.dialogItem.editar==true){
					this.atualizar();
				}else if (this.config.dialogItem.editar==false){
					this.criar();
				}
			}
		},
		criar(){
			let headers = {
					'Authorization': 'Bearer MEU_TOKEN',
					'Content-Type': 'application/json'
				};
			this.$axios.post(this.api.principal, this.getItemSelecionado(), {headers}).then(
				(response) => {
					this.limparItemSelecionado();
					this.pesquisar();
					this.config.dialogItem.abrir=false;
				}
			).catch(
				(error) => {
					console.error('Erro:', error);
				}
			);
		},
		atualizar(){
			this.$axios.put().then(
				(response) => {
					this.limparItemSelecionado();
					this.pesquisar();
					this.config.dialogItem.abrir=false;
				}
			).catch(
				(error) => {
					console.error('Erro:', error);
				}
			);
		},
		updatePagination(options){
			console.log(options);
			this.config.table.pagination.size = options.itemsPerPage;
			this.config.table.pagination.page = options.page-1;
			this.config.table.pagination.sort = options.sortBy.map(s => `${s.key},${s.order}`);
			this.pesquisar();
		},
		pesquisar() {
			let headers = {
					'Authorization': 'Bearer MEU_TOKEN',
					'Content-Type': 'application/json'
				};
			this.$axios.get(this.api.principal, {params: this.getFiltro(), headers:headers}).then(
				(response) => {
					this.config.table.pagination.totalItems = response.data.totalElements;
					if (response.data.content.length > 0){
						this.consulta.resultado = response.data.content;
					}else{
						this.consulta.resultado = [];
					}
				}
			).catch(
				(error) => {
					console.error('Erro:', error);
				}
			);
		},
		getItemSelecionado(){
			let bean = Object.assign({},this.itemSelecionado);
			return JSON.stringify(bean);
		},
		getFiltro() {
			let filtro = Object.assign(this.config.table.pagination, this.consulta.filtro);
			return filtro;
		},
		increment() {
			this.count++
		}
	},
});

app.use(_vuetify);

app.config.globalProperties.$axios = _axios;

app.mount('#app');


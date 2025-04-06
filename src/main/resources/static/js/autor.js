const app = Vue.createApp({
	created() {
		this.carregaDominio();
	},
	computed() { },
	mounted() { window.comp = this; },
	watch: { },
	data() {
		return {
			count: 0,
			config: {
				table: {
					loading: false,
					pagination: {
						itemsPerPage: 5,
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
			let filtro = Object.assign({}, this.consulta.filtro);
			return filtro;
		},
		increment() {
			this.count++
		}
	},
});

const vuetify = Vuetify.createVuetify({
	defaults: {
		global: {
//			density: 'compact',
		},
	},
	theme: {
		defaultTheme: 'light',
	},
})

app.use(vuetify);

app.mount('#app');

app.config.globalProperties.$axios = axios;

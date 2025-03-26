const app = Vue.createApp({
	created() {
		this.carregaDominio();
	},
	computed() { 
	},
	mounted() { },
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
				}
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
		criar() {},
		salvar() {},
		editar(item) {},
		deletar() {},
		pesquisar() {
			let headers = {
					'Authorization': 'Bearer MEU_TOKEN',
					'Content-Type': 'application/json'
				};
			this.$axios.get(this.api.principal, {params: this.getFiltro(), headers:headers}).then(
				(response) => {
					this.consulta.resultado = response.data;
				}
			).catch(
				(error) => {
					console.error('Erro:', error);
				}
			);
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

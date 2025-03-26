const app = Vue.createApp({
	mounted() {
		console.log(`The initial count is ${this.count}.`)
	},
	data() {
		return {
			count: 0,
			config: {
				table: {
					loading: false,
					pagination: {
						itemsPerPage: 5,
						totalItems: 0,
					}
				}
			},
			headers: [
				{ title: 'ID', key: 'id',align: 'center', sortable: true, },
				{ title: 'Titulo', key: 'titulo', align: 'center', sortable: true, },
				{ title: 'Descricao', key: 'descricao', align: 'center', sortable: true, },
			],
			itens:[],
		}
	},
	methods: {
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


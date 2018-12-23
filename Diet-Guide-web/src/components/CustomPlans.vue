  <template>
   <div>
    <el-carousel :autoplay="false" type="card" height="650px">
      <el-carousel-item v-for="recommendation in propsObj.response" :key="propsObj.response.indexOf(recommendation)">
          <el-card>
              <div class="product-card">
              <h1>Recommendation Set {{propsObj.response.indexOf(recommendation) + 1}}</h1>
              <el-popover
                v-for="product in recommendation.productsList" :key="product.sku"
                v-if="!isDuplicate(product)"
                placement="right"
                width="400"
                trigger="click">
                    <div class="product-details">
                        <div class="product-image">
                            <img :src="product.imageUrl">
                        </div>
                        <div class="product-info">
                            <h4>Product Name</h4> {{product.productName}}
                            <h4>Price</h4> {{product.price}}
                            <h4>Nutrition Index</h4> {{product.nutritionIndex}}
                        </div>
                    </div>
                <el-button slot="reference" icon="el-icon-info" size="mini">{{product.productName}}</el-button>
              </el-popover>
              </div>
          </el-card>
      </el-carousel-item>
    </el-carousel>
   </div>
  </template>

  <script>
  export default {
    name: 'custom-plans',
    data() {
        return {
            products: [],
            duplicateProducts: new Set(),
        }
    },
    props: {
      propsObj: {
        type: Object,
        default: function() {
            return {
                response: [],
            }
        },
      }
    },
    methods: {
        isDuplicate(product) {
            if (!product.hasOwnProperty("productName")){
                return true;
            }
            if (!this.duplicateProducts.has(product.sku)){
                this.duplicateProducts.add(product.sku);
                return false;
            } 
            return true;
        },
        search(recommendation){
            this.products = recommendation.productsList.map(element => {
                return {
                    name: element.productName,
                    id: element.productId,
                };
            });
        }
    }
  }
  </script>

  <style>
    .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(n) {
    background-color: #d3dce6;
  }

  img {
    max-width: 100%;
    height: auto;
}

.product-card {
    display: flex;
    height: 650px;
    flex-direction: column;
}

.product-details {
    display: flex;
    justify-content: space-around;
}

.product-info {
    vertical-align: middle;
    padding: 0px;
}

.product-image {
    margin: 30px 15px 15px 15px;
    width: 150px;
    height: 150px;
    padding: 3px;
    border-style: solid;
    border-width: 2px;
}
  </style>